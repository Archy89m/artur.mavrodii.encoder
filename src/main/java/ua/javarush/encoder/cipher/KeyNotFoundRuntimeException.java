package ua.javarush.encoder.cipher;

public class KeyNotFoundRuntimeException extends RuntimeException {

    public KeyNotFoundRuntimeException(String message) {
        super(message);
    }
}