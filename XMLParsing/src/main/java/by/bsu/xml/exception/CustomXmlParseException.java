package by.bsu.xml.exception;

public class CustomXmlParseException extends Exception {
    public CustomXmlParseException() {
        super();
    }

    public CustomXmlParseException(String message) {
        super(message);
    }

    public CustomXmlParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomXmlParseException(Throwable cause) {
        super(cause);
    }
}
