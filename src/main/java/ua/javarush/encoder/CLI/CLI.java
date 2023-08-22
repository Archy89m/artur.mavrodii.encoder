package ua.javarush.encoder.CLI;

import ua.javarush.encoder.App.Application;
import ua.javarush.encoder.App.Command;
import ua.javarush.encoder.App.Mode;
import ua.javarush.encoder.FileService;

import java.nio.file.Path;
import java.util.Scanner;

public class CLI {

    public static void StartApp(String[] args) {

        choseMode();
        chooseCommand(args);
        chooseFile(args);

    }
    public static void choseMode() {

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
    public static void chooseCommand(String[] args) {
        if (Application.getMode() == Mode.CLI){
            choseCommandCLI();
        } else if (Application.getMode() == Mode.ARGS) {
            commandARGS(args);
        }
    }
    public static void choseCommandCLI() {

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
    public static void commandARGS(String[] args) {
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
    public static void chooseFile(String[] args) {
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
}

