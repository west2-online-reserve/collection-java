package Animal;

/**
 *
 * @author 12080
 * 一个自定义异常
 * 用于处理余额不足的情况
 *
 **/
public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException(){}

    public InsufficientBalanceException(String message){
        super(message);
    }
}
