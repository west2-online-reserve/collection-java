package petshop.exception;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(){}
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
