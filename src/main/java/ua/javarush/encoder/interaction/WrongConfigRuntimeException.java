package ua.javarush.encoder.interaction;

public class WrongConfigRuntimeException extends RuntimeException {

    public WrongConfigRuntimeException (String message) {
        super(message);
    }

    public WrongConfigRuntimeException (String message, Throwable e) {
        super(message, e);
    }
}