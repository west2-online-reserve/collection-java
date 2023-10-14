package animalshop;

/**
 * 余额不足抛出异常
 */
public class InsufficientBalanceException extends RuntimeException{

    /**
     * 空参构造
     */
    public InsufficientBalanceException() {}

    /**
     * 有参构造
     */
    public InsufficientBalanceException(String message) {
        super(message);
    }

}
