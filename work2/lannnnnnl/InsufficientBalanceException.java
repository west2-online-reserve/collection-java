package westwork2;

//自定义余额不足异常
public class InsufficientBalanceException extends RuntimeException {
 InsufficientBalanceException(String message) {
     super(message);
 }
}
