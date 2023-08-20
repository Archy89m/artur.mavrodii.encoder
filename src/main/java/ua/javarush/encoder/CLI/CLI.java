package ua.javarush.encoder.CLI;

import ua.javarush.encoder.Runner.Command;
import ua.javarush.encoder.Runner.Mode;
import ua.javarush.encoder.Runner.Runner;

import java.util.Scanner;

public class CLI {

    public static void StartApp() {

        choseMode();
        yourMode();

    }

    public static void choseMode() {

        System.out.println("Chose app mode, 1 - CLI, 2 - Arguments, 3 - Exit:");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            if (!scanner.hasNextInt())
                System.out.println("Choose integer");
            int mode = scanner.nextInt();
            if (mode == 1) {
                Runner.setMode(Mode.CLI);
                break;
            } else if (mode == 2) {
                Runner.setMode(Mode.ARGS);
                break;
            } else if (mode == 3){
                System.out.println("Bye, see you next time :)");
                System.exit(0);
            }
        }
     }

    public static void yourMode() {
        System.out.println("Your mode: " + Runner.getMode());
    }
}

