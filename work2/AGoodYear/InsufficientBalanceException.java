/**
 * @author AGoodYear
 * @date 2023/11/4
 */

public class InsufficientBalanceException extends RuntimeException{
    private double balance;
    private double price;
    public InsufficientBalanceException(double balance, double price) {
        this.balance = balance;
        this.price = price;
    }
    public void printError() {
        System.out.println("余额不足！余额" + balance + "元，而购买该动物需要" + price +"元");
    }
}
