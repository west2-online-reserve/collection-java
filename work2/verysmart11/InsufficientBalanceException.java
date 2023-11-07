package work2;

public class InsufficientBalanceException extends RuntimeException{
    protected InsufficientBalanceException() {
    }

    protected InsufficientBalanceException(String message) {
        super(message);
    }

    protected InsufficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    protected InsufficientBalanceException(Throwable cause) {
        super(cause);
    }

    protected InsufficientBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
