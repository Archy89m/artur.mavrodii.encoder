package ua.javarush.encoder.App;

import ua.javarush.encoder.CLI.CLI;

import java.nio.file.Path;

public class Application {
    private static Mode mode;
    private static Command command;
    private static Path filePath;

    public static void main(String[] args) {

        CLI.StartApp(args);

    }

    public static Mode getMode() {
        return mode;
    }
    public static void setMode(Mode mode) {
        Application.mode = mode;
    }
    public static Command getCommand() {
        return command;
    }
    public static void setCommand(Command command) {
        Application.command = command;
    }
    public static Path getFilePath() {
        return filePath;
    }
    public static void setFilePath(Path filePath) {
        Application.filePath = filePath;
    }
}
