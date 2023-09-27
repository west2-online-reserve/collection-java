package animal;//这里是按照任务定义的一个自定义异常，就是宠物店想要批发，但是没钱了

public class InsufficientBalanceException extends RuntimeException{

    public InsufficientBalanceException() {
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
