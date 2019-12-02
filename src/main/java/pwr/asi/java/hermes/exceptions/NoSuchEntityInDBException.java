package pwr.asi.java.hermes.exceptions;

public class NoSuchEntityInDBException extends Exception {
    public NoSuchEntityInDBException() {
    }

    public NoSuchEntityInDBException(String message) {
        super(message);
    }
}
