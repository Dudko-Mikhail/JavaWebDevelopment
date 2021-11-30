package by.bsu.task6.exception;

public class TunnelException extends Exception {
    public TunnelException() {
        super();
    }

    public TunnelException(String message) {
        super(message);
    }

    public TunnelException(String message, Throwable cause) {
        super(message, cause);
    }

    public TunnelException(Throwable cause) {
        super(cause);
    }
}
