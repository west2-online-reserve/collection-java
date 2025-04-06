package learn_java1;

public class InsufficientBalanceException extends RuntimeException {
    private final String message;

    public InsufficientBalanceException() {
        message = "店内余额不足";
    }

    void get_Message() {
        System.out.println(message);
    }
}
