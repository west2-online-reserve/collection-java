package java_work2;

/**
 * InsufficientBalanceException异常处理类
 *
 * @author formrc
 * @date 2023/10/25
 */
class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
