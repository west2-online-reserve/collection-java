package MySystem.MyException;

public class OrdersNotFoundException extends RuntimeException {
    public OrdersNotFoundException(String message) {
        super(message);
    }
}
