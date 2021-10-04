package by.bsu.firsttask.exception;

public class CustomArrayException extends Exception {
    public CustomArrayException() {}

    public CustomArrayException(String message) {
        super(message);
    }

    public CustomArrayException(Throwable cause) {
        super(cause);
    }

    public CustomArrayException(String message, Throwable cause) {
        super(message, cause);
    }
}
