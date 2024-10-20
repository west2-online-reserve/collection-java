package exception;

public class OpenException extends RuntimeException {
    public OpenException() {
    }

    public OpenException(String message) {
        super(message);
    }

    public OpenException(String message, Throwable cause) {
        super(message, cause);
    }

    public OpenException(Throwable cause) {
        super(cause);
    }

    public OpenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
