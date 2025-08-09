package HomeWork;

public class InsufficientBalanceException extends RuntimeException {
    String message;
    public InsufficientBalanceException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
