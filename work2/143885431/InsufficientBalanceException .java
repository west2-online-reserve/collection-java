/**
 * @author think
 */
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(){
    }
    public InsufficientBalanceException(String msg){
        super(msg);
    }

    @Override
    public String toString() {
        return "余额不足";
    }
}
