package work2;

/**
 * @author Mia
 * @date 2023/11/1
 */
public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
