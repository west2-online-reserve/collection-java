public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        System.out.println("店内余额不足");
        System.out.println("提前关门");

    }
}
