package homework.work2;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message) {
        super("余额不足！无法进货");
    }
}
