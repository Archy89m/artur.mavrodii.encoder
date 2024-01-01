package ua.javarush.encoder.cipher;

class KeyNotFoundRuntimeException extends RuntimeException {

    KeyNotFoundRuntimeException(String message) {
        super(message);
    }
}