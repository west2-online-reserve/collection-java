package Exception;

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(String message) {
        super(message);

    }
    public InsufficientBalanceException() {
        super("余额不足，无法购买该动物");

    }
}
