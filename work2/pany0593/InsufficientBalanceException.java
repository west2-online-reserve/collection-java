/**
 * 余额不足异常时抛出
 *
 * @author pany0593
 * @date 2023/11/5
 */
public class InsufficientBalanceException extends RuntimeException {
    private String message;

    InsufficientBalanceException(String message) {
        super(message);
        this.message = message;
    }
}
