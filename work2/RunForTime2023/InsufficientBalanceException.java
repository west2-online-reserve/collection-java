public class InsufficientBalanceException extends RuntimeException {
    InsufficientBalanceException() {
        super("The balance isn't enough!");
    }
}