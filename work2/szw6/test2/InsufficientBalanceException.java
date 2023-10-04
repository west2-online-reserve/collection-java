package szw.test2;

public class InsufficientBalanceException extends RuntimeException{


    public InsufficientBalanceException(String message) {
        super(message);
    }
}
