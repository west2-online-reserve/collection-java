package PetShop;

/**
 * @author Jst599
 * @date 2023/10/18
 */
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
