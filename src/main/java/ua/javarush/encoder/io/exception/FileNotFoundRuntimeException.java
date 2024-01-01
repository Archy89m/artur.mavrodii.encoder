package ua.javarush.encoder.io.exception;

public class FileNotFoundRuntimeException extends RuntimeException {

    public FileNotFoundRuntimeException (String message) {
        super(message);
    }
}