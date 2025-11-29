package shop.myexception;

/**
 * Describe:
 *
 * @author cky
 *
 * @date 2025/10/22
 */
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message) {
        super("余额不足： "+message);
    }
}
