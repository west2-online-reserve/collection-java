public class InsufficientBalanceException extends RuntimeException {

    private double currentBalance; // 当前余额
    private double requiredAmount; // 购买所需金额

    public InsufficientBalanceException(String message, double currentBalance, double requiredAmount) {
        super(message);
        this.currentBalance = currentBalance;
        this.requiredAmount = requiredAmount;
    }
}
