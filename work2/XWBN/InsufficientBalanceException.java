package XWBN2;

public class InsufficientBalanceException extends RuntimeException {
    public String animalName;
    public double account;

    public InsufficientBalanceException(String aniName, double account) {
        this.animalName = aniName;
        this.account = account;
    }

    public String toString() {
        return "账户余额不足无法购买" + animalName + '\n';
    }

}
