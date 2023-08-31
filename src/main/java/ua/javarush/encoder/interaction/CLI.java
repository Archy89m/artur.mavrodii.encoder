package ua.javarush.encoder.interaction;

import ua.javarush.encoder.alphabet.AlphabetUtil;
import ua.javarush.encoder.cipher.CaesarCipher;
import ua.javarush.encoder.io.FileService;
import ua.javarush.encoder.setting.Command;
import ua.javarush.encoder.setting.Config;
import ua.javarush.encoder.setting.Mode;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class CLI {

    public void StartApp(Config config, String[] args) {

        chooseMode(config);
        chooseCommand(config, args);
        chooseFile(config, args);
        chooseKey(config, args);
    }

    public void StartWorkingWithFile(Config config) {

        FileService fileService = new FileService();
        List<String> allLines = fileService.reading(config.getFilePath());
        List<String> cryptoLines;
        String newPartFileName;

        chooseAlphabet(config, allLines.get(0));

        CaesarCipher caesarCipher = new CaesarCipher();
        if (config.getCommand() == Command.BRUTE_FORCE) {
            int key = caesarCipher.cryptoFindKey(allLines, config);
            newPartFileName = "(B key-" + key + ").";
            config.setCommand(Command.DECRYPT);
            config.setKey(key);
        } else {
            newPartFileName = "[" + config.getCommand() + "].";
        }
        cryptoLines = caesarCipher.cryptoOperate(allLines, config);
        fileService.writeFile(config.getFilePath(), newPartFileName, cryptoLines);
    }

    private void chooseMode(Config config) {

        System.out.println("Chose app mode, 1 - CLI, 2 - Arguments");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if (mode == 1) {
            config.setMode(Mode.CLI);
        } else if (mode == 2) {
            config.setMode(Mode.ARGS);
        } else {
            throw new WrongConfigRuntimeException("Unknown mode");
        }
    }

    private void chooseCommand(Config config, String[] args) {

        if (config.getMode() == Mode.CLI){
            chooseCommandCLI(config);
        } else if (config.getMode() == Mode.ARGS) {
            commandARGS(config, args);
        }
    }

    private void chooseCommandCLI(Config config) {

        System.out.println("Chose command, 1 - ENCRYPT, 2 - DECRYPT, 3 - BRUTE_FORCE");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if (mode == 1) {
            config.setCommand(Command.ENCRYPT);
        } else if (mode == 2) {
            config.setCommand(Command.DECRYPT);
        } else if (mode == 3) {
            config.setCommand(Command.BRUTE_FORCE);
        } else {
            throw new WrongConfigRuntimeException("Wrong command");
        }
    }

    private static void commandARGS(Config config, String[] args) {

        try {
            switch (args[0]) {
                case "ENCRYPT" -> config.setCommand(Command.ENCRYPT);
                case "DECRYPT" -> config.setCommand(Command.DECRYPT);
                case "BRUTE_FORCE" -> config.setCommand(Command.BRUTE_FORCE);
                default -> throw new WrongConfigRuntimeException("Unknown command from argument");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void chooseFile(Config config, String[] args) {

        FileService fileService = new FileService();

        Path path = null;
        if (config.getMode() == Mode.CLI) {
            System.out.println("Write file name");
            path = fileService.getFilePath(new Scanner(System.in).nextLine());
        } else if (config.getMode() == Mode.ARGS) {
            path = fileService.getFilePath(args[1]);
        }
        config.setFilePath(path);
    }

    private static void chooseKey(Config config, String[] args) {

        if (config.getCommand() == Command.BRUTE_FORCE)
            return;

        if (config.getMode() == Mode.CLI) {
            System.out.println("Write integer key");
            String keyString = new Scanner(System.in).nextLine();
            try {
                config.setKey(Integer.parseInt(keyString));
            } catch (NumberFormatException e) {
                throw new WrongConfigRuntimeException("Invalid key format", e);
            }
        } else if (config.getMode() == Mode.ARGS) {
            try {
                String keyString = args[2];
                config.setKey(Integer.parseInt(keyString));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new WrongConfigRuntimeException("Wrong arguments", e);
            }
        }
    }

    private static void chooseAlphabet(Config config, String line) {
        config.setAlphabet(new AlphabetUtil().detectLanguage(line));
    }
}
