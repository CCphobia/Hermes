package pwr.asi.java.hermes.exceptions;

public class NoSuchEntityInDBException extends RuntimeException {
    public NoSuchEntityInDBException() {
    }

    public NoSuchEntityInDBException(String message) {
        super(message);
    }
}
