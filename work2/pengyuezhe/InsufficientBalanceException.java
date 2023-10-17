/**
 * @author pengyuezhe
 */
public class InsufficientBalanceException extends RuntimeException {
    private double amount;

    public InsufficientBalanceException(double needs) {
        amount = needs;
    }

    @Override
    public String toString() {
        return "宠物店余额不足需要" + amount;
    }
}
