package ua.javarush.encoder;

import ua.javarush.encoder.alphabet.AlphabetUtil;
import ua.javarush.encoder.application.Application;
import ua.javarush.encoder.application.Command;
import ua.javarush.encoder.application.Mode;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class CLI {

    public void StartApp(Application crypto, String[] args) {

        chooseMode(crypto);
        chooseCommand(crypto, args);
        chooseFile(crypto, args);
        chooseKey(crypto, args);
    }

    public void StartWorkingWithFile(Application crypto) {

        FileService fileService = new FileService();
        List<String> allLines = fileService.reading(crypto.getFilePath());
        List<String> cryptoLines;
        String newPartFileName;

        chooseAlphabet(crypto, allLines.get(0));

        CaesarCipher caesarCipher = new CaesarCipher();
        if (crypto.getCommand() == Command.BRUTE_FORCE) {
            int key = caesarCipher.cryptoFindKey(allLines, crypto);
            newPartFileName = "(B key-" + key + ").";
            crypto.setCommand(Command.DECRYPT);
            crypto.setKey(key);
        } else {
            newPartFileName = "[" + crypto.getCommand() + "].";
        }
        cryptoLines = caesarCipher.cryptoOperation(allLines, crypto);
        fileService.writeFile(crypto.getFilePath(), newPartFileName, cryptoLines);
    }

    private void chooseMode(Application crypto) {

        System.out.println("Chose app mode, 1 - CLI, 2 - Arguments");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if (mode == 1) {
            crypto.setMode(Mode.CLI);
        } else if (mode == 2) {
            crypto.setMode(Mode.ARGS);
        } else {
            throw new RuntimeException("Wrong number, see you next time :)");
        }
    }

    private void chooseCommand(Application crypto, String[] args) {

        if (crypto.getMode() == Mode.CLI){
            chooseCommandCLI(crypto);
        } else if (crypto.getMode() == Mode.ARGS) {
            commandARGS(crypto, args);
        }
    }

    private void chooseCommandCLI(Application crypto) {

        System.out.println("Chose command, 1 - ENCRYPT, 2 - DECRYPT, 3 - BRUTE_FORCE");
        Scanner scanner = new Scanner(System.in);
        int mode = scanner.nextInt();
        if (mode == 1) {
            crypto.setCommand(Command.ENCRYPT);
        } else if (mode == 2) {
            crypto.setCommand(Command.DECRYPT);
        } else if (mode == 3) {
            crypto.setCommand(Command.BRUTE_FORCE);
        } else {
            throw new RuntimeException("Wrong number, bye, see you next time :)");
        }
    }

    private static void commandARGS(Application crypto, String[] args) {

        try {
            switch (args[0]) {
                case "ENCRYPT" -> crypto.setCommand(Command.ENCRYPT);
                case "DECRYPT" -> crypto.setCommand(Command.DECRYPT);
                case "BRUTE_FORCE" -> crypto.setCommand(Command.BRUTE_FORCE);
                default -> throw new RuntimeException("Unknown command argument, please try again");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void chooseFile(Application crypto, String[] args) {

        FileService fileService = new FileService();

        if (crypto.getMode() == Mode.CLI) {
            System.out.println("Write file name");
            Path path = fileService.getFilePath(new Scanner(System.in).nextLine());
            crypto.setFilePath(path);
        } else if (crypto.getMode() == Mode.ARGS) {
            try {
                Path path = fileService.getFilePath(args[1]);
                crypto.setFilePath(path);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void chooseKey(Application crypto, String[] args) {

        if (crypto.getCommand() == Command.BRUTE_FORCE)
            return;

        if (crypto.getMode() == Mode.CLI) {
            System.out.println("Write integer key");
            String keyString = new Scanner(System.in).nextLine();
            try {
                crypto.setKey(Integer.parseInt(keyString));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException("Invalid key format");
            }
        } else if (crypto.getMode() == Mode.ARGS) {
            try {
                String keyString = args[2];
                crypto.setKey(Integer.parseInt(keyString));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void chooseAlphabet(Application crypto, String line) {
        crypto.setAlphabet(new AlphabetUtil().detectLanguage(line));
    }
}
