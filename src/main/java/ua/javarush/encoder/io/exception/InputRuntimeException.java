package ua.javarush.encoder.io.exception;

public class InputRuntimeException extends RuntimeException {

    public InputRuntimeException (String message, Throwable e) {
        super(message, e);
    }
}