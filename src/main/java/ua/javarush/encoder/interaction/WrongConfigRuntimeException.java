package ua.javarush.encoder.interaction;

class WrongConfigRuntimeException extends RuntimeException {

    WrongConfigRuntimeException (String message) {
        super(message);
    }

    WrongConfigRuntimeException (String message, Throwable e) {
        super(message, e);
    }
}