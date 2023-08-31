package ua.javarush.encoder.io.exception;

public class OutputRuntimeException extends RuntimeException {

    public OutputRuntimeException (String message, Throwable e) {
        super(message, e);
    }
}