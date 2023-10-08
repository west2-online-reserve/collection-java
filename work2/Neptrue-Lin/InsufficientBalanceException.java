public class InsufficientBalanceException extends RuntimeException {
    protected double balance;
    protected double expectedCost;

    public InsufficientBalanceException(double balance, double expectedCost) {
        super("Balance is insufficient to cover the cost!");
        this.setBalance(balance);
        this.setExpectedCost(expectedCost);
    }

    public double getBalance() {
        return this.balance;
    }

    protected void setBalance(double balance) throws IllegalArgumentException{
        if (balance < 0) {
            throw new IllegalArgumentException("Balance should not be less than 0!");
        }
        this.balance = balance;
    }

    public double getExpectedCost() {
        return this.expectedCost;
    }

    protected void setExpectedCost(double expectedCost) throws IllegalArgumentException{
        if (expectedCost < 0) {
            throw new IllegalArgumentException("Expected cost should not be less than 0!");
        }
        this.expectedCost = expectedCost;
    }
}
