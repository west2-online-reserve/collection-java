package Work1;

public class InsufficientBalanceException extends RuntimeException {
    private double value;
    private double balance;

    public InsufficientBalanceException() {
    }

    public InsufficientBalanceException(String message, double balance, double value) {
        super(message);
        this.value = value;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "店铺余额不足,店铺余额:" + balance + ",预购动物价格:" + value;
    }

}
