package ua.javarush.encoder;

import ua.javarush.encoder.alphabet.EnglishAlphabet;
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

        System.out.println("Start reading file...");
        System.out.println("................");

        FileService fileService = new FileService();
        List<String> allLines = fileService.reading(crypto.getFilePath());
        for (String line:allLines)
            System.out.println(line);
        System.out.println("................");

        chooseAlphabet(crypto);
        System.out.println("Language was chosen - " + crypto.getAlphabet());
        System.out.println("................");

        System.out.println("Start " + crypto.getCommand() + " with key " + crypto.getKey());
        System.out.println("................");

        CaesarCipher caesarCipher = new CaesarCipher();
        List<String> cryptoLines = caesarCipher.cryptoOperation(allLines, crypto);
        for (String line:cryptoLines)
            System.out.println(line);
        System.out.println("................");
        String newPartFileName = "[" + crypto.getCommand() + "].";
        fileService.writeFile(crypto.getFilePath(), newPartFileName, cryptoLines);
    }

    private void chooseMode(Application crypto) {

        System.out.println("Chose app mode, 1 - CLI, 2 - Arguments, 3 - Exit:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            if (!scanner.hasNextInt())
                System.out.println("Choose integer");
            int mode = scanner.nextInt();
            if (mode == 1) {
                crypto.setMode(Mode.CLI);
                break;
            } else if (mode == 2) {
                crypto.setMode(Mode.ARGS);
                break;
            } else if (mode == 3){
                System.out.println("Bye, see you next time :)");
                System.exit(0);
            }
        }
        System.out.println("Your mode: " + crypto.getMode());
    }

    private void chooseCommand(Application crypto, String[] args) {

        if (crypto.getMode() == Mode.CLI){
            chooseCommandCLI(crypto);
        } else if (crypto.getMode() == Mode.ARGS) {
            commandARGS(crypto, args);
        }
    }

    private void chooseCommandCLI(Application crypto) {

        System.out.println("Chose command, 1 - ENCRYPT, 2 - DECRYPT, 3 - BRUTE_FORCE, 4 - Exit:");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            if (!scanner.hasNextInt())
                System.out.println("Choose integer");
            int mode = scanner.nextInt();
            if (mode == 1) {
                crypto.setCommand(Command.ENCRYPT);
                break;
            } else if (mode == 2) {
                crypto.setCommand(Command.DECRYPT);
                break;
            } else if (mode == 3) {
                //Application.setCommand(Command.BRUTE_FORCE);
                //break;
                System.out.println("Command BRUTE_FORCE is still in development, please choose another command");
            } else if (mode == 4){
                System.out.println("Bye, see you next time :)");
                System.exit(0);
            }
        }
        System.out.println("Your command: " + crypto.getCommand());
    }

    private static void commandARGS(Application crypto, String[] args) {

        try {
            if (args[0].equals("ENCRYPT")) {
                crypto.setCommand(Command.ENCRYPT);
                System.out.println("Your command - " + Command.ENCRYPT);
            } else if (args[0].equals("DECRYPT")) {
                crypto.setCommand(Command.DECRYPT);
                System.out.println("Your command - " + Command.DECRYPT);
            } else if (args[0].equals("BRUTE_FORCE")) {
                System.out.println("Command BRUTE_FORCE is still in development, please choose another command");
                System.exit(0);
                //Application.setCommand(Command.BRUTE_FORCE);
            } else {
                System.out.println("Unknown command argument, please try again");
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
        System.out.println("Key - " + crypto.getKey());
    }

    private static void chooseAlphabet(Application crypto) {
        crypto.setAlphabet(EnglishAlphabet.getInstance());
    }
}
