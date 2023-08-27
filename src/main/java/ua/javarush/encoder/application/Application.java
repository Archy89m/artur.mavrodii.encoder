package ua.javarush.encoder.application;

import ua.javarush.encoder.alphabet.Alphabet;
import ua.javarush.encoder.CLI;

import java.nio.file.Path;

public class Application {

    private Mode mode;
    private Command command;
    private Path filePath;
    private int key;
    private Alphabet alphabet;

    public static void main(String[] args) {

        Application crypto = new Application();

        CLI cli = new CLI();
        cli.StartApp(crypto, args);
        cli.StartWorkingWithFile(crypto);
    }

    public Mode getMode() {
        return this.mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Command getCommand() {
        return this.command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Path getFilePath() {
        return this.filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public int getKey() {
        return this.key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public void setAlphabet(Alphabet alphabet) {
        this.alphabet = alphabet;
    }
}
