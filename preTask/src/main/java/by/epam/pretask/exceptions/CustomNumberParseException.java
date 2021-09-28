package by.epam.pretask.exceptions;

public class CustomNumberParseException extends Exception {
    public CustomNumberParseException() {
        super();
    }

    public CustomNumberParseException(String message) {
        super(message);
    }

    public CustomNumberParseException(Throwable cause) {
        super(cause);
    }

    public CustomNumberParseException(String message, Throwable cause) {
        super(message, cause);
    }

}
