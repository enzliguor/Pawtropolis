package pawtropolis.exception;

public class MarshallerNotFoundException extends RuntimeException {
    public MarshallerNotFoundException() {
        super();
    }

    public MarshallerNotFoundException(String message) {
        super(message);
    }

    public MarshallerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
