package work2;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
    }

    @Override
    public String toString() {
        return "你没钱辣！";
    }
}
