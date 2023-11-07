package Animal;

/**
 *
 * @author 12080
 * 一个自定义异常
 * 用于处理没有动物的情况
 *
 **/


public class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException() {}
    public AnimalNotFountException(String message) {
        super(message);
    }
}
