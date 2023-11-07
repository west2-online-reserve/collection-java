/**
 * @author aozne
 * @date 2023/10/23 21:12
 **/
public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String messaage){
        super(messaage);
    }
}
