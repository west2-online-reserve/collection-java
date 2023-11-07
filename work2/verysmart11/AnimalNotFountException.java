package work2;

public class AnimalNotFountException extends  RuntimeException{
    protected AnimalNotFountException() {
    }

    protected AnimalNotFountException(String message) {
        super(message);
    }

    protected AnimalNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    protected AnimalNotFountException(Throwable cause) {
        super(cause);
    }

    protected AnimalNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}