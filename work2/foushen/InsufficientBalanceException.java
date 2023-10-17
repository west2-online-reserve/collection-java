public class InsufficientBalanceException extends RuntimeException {
    private double restMoney;

    public InsufficientBalanceException(String message, double restMoney) {
        super(message);
        this.restMoney = restMoney;
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }

    public String toString() {
        return getMessage() + " " + restMoney;
    }
}
