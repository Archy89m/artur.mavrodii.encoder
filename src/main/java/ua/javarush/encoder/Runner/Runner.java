package ua.javarush.encoder.Runner;

import java.util.Scanner;

public class Runner {
    private static Mode mode;
    private static Command command;
    public static Mode getMode() {
        return mode;
    }
    public static void setMode(Mode mode) {
        Runner.mode = mode;
    }
    public static Command getCommand() {
        return command;
    }
    public static void setCommand(Command command) {
        Runner.command = command;
    }

}
