package example;

public class AnimalNotFountException extends RuntimeException {
    public AnimalNotFountException() {
        super();
    }

    public AnimalNotFountException(String message) {
        super(message);
    }

    public AnimalNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnimalNotFountException(Throwable cause) {
        super(cause);
    }

    public AnimalNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
