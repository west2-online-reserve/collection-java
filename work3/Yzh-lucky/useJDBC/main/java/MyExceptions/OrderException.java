package MyExceptions;

public class OrderException extends RuntimeException{
    public OrderException(String message){
        super(message);
    }
}
