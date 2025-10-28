public class InsufficientBalanceException extends RuntimeException {
    // 无参构造方法
    public InsufficientBalanceException() {
        super();
    }

    // 带错误信息的构造方法
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
