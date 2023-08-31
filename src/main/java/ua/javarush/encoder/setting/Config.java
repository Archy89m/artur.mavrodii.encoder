package ua.javarush.encoder.setting;

import ua.javarush.encoder.alphabet.Alphabet;

import java.nio.file.Path;

public class Config {
    private Mode mode;
    private Command command;
    private Path filePath;
    private int key;
    private Alphabet alphabet;


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