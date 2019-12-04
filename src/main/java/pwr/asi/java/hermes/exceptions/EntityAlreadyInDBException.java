package pwr.asi.java.hermes.exceptions;

public class EntityAlreadyInDBException extends RuntimeException {
    public EntityAlreadyInDBException() {
    }

    public EntityAlreadyInDBException(String message) {
        super(message);
    }
}
