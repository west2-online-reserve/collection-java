/*当前顾客余额不足以购买动物时抛出异常*/
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
