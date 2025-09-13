public class InsufficientBalanceException extends RuntimeException {
    private String name;
    private double price;
    private double balance;

    public InsufficientBalanceException() {
    }

    public InsufficientBalanceException(String name, double price, double balance) {
        this.name = name;
        this.price = price;
        this.balance = balance;
    }

    public void printDetailedError() {
        System.out.println("购买" + name + "失败");
        System.out.println("该动物的价格为" + price);
        System.out.println("余额为" + balance);
        System.out.println("还差" + (price - balance) + "元");
    }
}