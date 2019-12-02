package pwr.asi.java.hermes.exceptions;

public class EntityAlreadyInDBException extends Exception {
    public EntityAlreadyInDBException() {
    }

    public EntityAlreadyInDBException(String message) {
        super(message);
    }
}
