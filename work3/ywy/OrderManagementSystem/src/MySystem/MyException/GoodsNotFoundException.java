package MySystem.MyException;

public class GoodsNotFoundException extends RuntimeException {
    public GoodsNotFoundException(String message) {
        super(message);
    }
}
