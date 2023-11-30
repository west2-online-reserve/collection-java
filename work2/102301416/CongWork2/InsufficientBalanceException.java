package CongWork2;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String massage){
        super(massage);
    }
}
