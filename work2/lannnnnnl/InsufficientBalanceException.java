package westwork2;

public class InsufficientBalanceException extends RuntimeException {
    private double balance;
    private double expectedCost;

    public InsufficientBalanceException(double balance, double expectedCost) {
        super("Insufficient balance: Available " + balance + " but expected " + expectedCost + ".");
        if (balance < 0) {
            throw new IllegalArgumentException("Balance should not be less than 0: " + balance);
        }
        if (expectedCost < 0) {
            throw new IllegalArgumentException("Expected cost should not be less than 0: " + expectedCost);
        }
        this.balance = balance;
        this.expectedCost = expectedCost;
    }

    public double getBalance() {
        return balance;
    }

    public double getExpectedCost() {
        return expectedCost;
    }
}

