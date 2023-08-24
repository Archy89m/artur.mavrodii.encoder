package ua.javarush.encoder;

import ua.javarush.encoder.Alphabet.Alphabet;
import ua.javarush.encoder.App.Application;
import ua.javarush.encoder.App.Command;
import ua.javarush.encoder.App.Mode;
import ua.javarush.encoder.FileService;

import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class CLI {

    public static void StartApp(String[] args) {
        choseMode();
        chooseCommand(args);
        chooseFile(args);
        choseKey(args);
    }

    public static void StartWorkingWithFile(Alphabet alphabet) {
        System.out.println("Start reading file...");
        System.out.println("................");
        List<String> allLines = FileService.reading(Application.getFilePath());
        for (String line:allLines)
            System.out.println(line);
        System.out.println("................");
        System.out.println("Start " + Application.getCommand() + " with key " + Application.getKey());
        System.out.println("................");
        List<String> cryptoLines = CaesarCipher.cryptoOperation(allLines, alphabet, Application.getCommand(), Application.getKey());
        for (String line:cryptoLines)
            System.out.println(line);
        System.out.println("................");
        FileService.writeFile(Application.getFilePath(), Application.getCommand(), cryptoLines);

    }
    private static void choseMode() {

        System.out.println("Chose app mode, 1 - CLI, 2 - Arguments, 3 - Exit:");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            if (!scanner.hasNextInt())
                System.out.println("Choose integer");
            int mode = scanner.nextInt();
            if (mode == 1) {
                Application.setMode(Mode.CLI);
                break;
            } else if (mode == 2) {
                Application.setMode(Mode.ARGS);
                break;
            } else if (mode == 3){
                System.out.println("Bye, see you next time :)");
                System.exit(0);
            }
        }
        System.out.println("Your mode: " + Application.getMode());
    }
    private static void chooseCommand(String[] args) {
        if (Application.getMode() == Mode.CLI){
            choseCommandCLI();
        } else if (Application.getMode() == Mode.ARGS) {
            commandARGS(args);
        }
    }
    private static void choseCommandCLI() {

        System.out.println("Chose command, 1 - ENCRYPT, 2 - DECRYPT, 3 - BRUTE_FORCE, 4 - Exit:");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            if (!scanner.hasNextInt())
                System.out.println("Choose integer");
            int mode = scanner.nextInt();
            if (mode == 1) {
                Application.setCommand(Command.ENCRYPT);
                break;
            } else if (mode == 2) {
                Application.setCommand(Command.DECRYPT);
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
        System.out.println("Your command: " + Application.getCommand());
    }
    private static void commandARGS(String[] args) {
        try {
            if (args[0].equals("ENCRYPT")) {
                Application.setCommand(Command.ENCRYPT);
                System.out.println("Your command - " + Command.ENCRYPT);
            } else if (args[0].equals("DECRYPT")) {
                Application.setCommand(Command.DECRYPT);
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
    private static void chooseFile(String[] args) {
        if (Application.getMode() == Mode.CLI) {
            System.out.println("Write file name");
            Path path = FileService.getFilePath(new Scanner(System.in).nextLine());
            Application.setFilePath(path);
        } else if (Application.getMode() == Mode.ARGS) {
            try {
                Path path = FileService.getFilePath(args[1]);
                Application.setFilePath(path);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    private static void choseKey(String[] args) {
        if (Application.getMode() == Mode.CLI) {
            System.out.println("Write integer key");
            String keyString = new Scanner(System.in).nextLine();
            try {
                Application.setKey(Integer.parseInt(keyString));
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                throw new RuntimeException("Invalid key format");
            }
        } else if (Application.getMode() == Mode.ARGS) {
            try {
                String keyString = args[2];
                Application.setKey(Integer.parseInt(keyString));
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Key - " + Application.getKey());
    }
}

