public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("The balance isn't enough!");
    }
}