package MySystem.MyException;

public class IllegalPriceException extends RuntimeException {
    public IllegalPriceException(String message) {
        super(message);
    }
}
