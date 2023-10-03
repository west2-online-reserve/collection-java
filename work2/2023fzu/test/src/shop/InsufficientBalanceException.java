package shop;

/**
 * @author HarveyBlocks
 * @date 2023/09/04 10:33
 **/
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
