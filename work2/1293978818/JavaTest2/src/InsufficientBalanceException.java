/**
 * 余额不足异常
 * @author 1293978818
 */
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message){
        super(message);
    }
}
