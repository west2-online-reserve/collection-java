package animal;

/**
 * @author MR.瑜
 */
public class InsufficientBalanceException extends RuntimeException{
    private double balance;
    private double expectedCost;
    public InsufficientBalanceException(double balance, double expectedCost) {
        super("Balance is insufficient to cover the cost!");
        this.setBalance(balance);
        this.setExpectedCost(expectedCost);
    }
    public double getBalance(){
        return this.balance;
    }
    protected void setBalance(double balance) {
        if (balance < 0) {
            System.out.println("Balance should not be less than 0!");
        }
        this.balance = balance;
    }
    public double getExpectedCost() {
        return this.expectedCost;
    }

    protected void setExpectedCost(double expectedCost) {
        if (expectedCost < 0) {
            System.out.println("Expected cost should not be less than 0!");
        }
        this.expectedCost = expectedCost;
    }
}
