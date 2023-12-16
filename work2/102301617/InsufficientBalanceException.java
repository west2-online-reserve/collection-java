public class InsufficientBalanceException extends RuntimeException{
    @Override
    public String toString() {
        return "InsufficientBalanceException{"+"余额不足"+'}';
    }
}
