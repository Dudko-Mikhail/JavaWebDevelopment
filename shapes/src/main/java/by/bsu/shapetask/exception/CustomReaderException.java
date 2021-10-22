package by.bsu.shapetask.exception;

public class CustomReaderException extends Exception {
    public CustomReaderException() {
        super();
    }

    public CustomReaderException(String message) {
        super(message);
    }

    public CustomReaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomReaderException(Throwable cause) {
        super(cause);
    }
}
