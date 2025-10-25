package shop.myexception;

/**
 * Describe:
 *
 * @author cky
 * @date 2025/10/22
 */
public class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException(String message) {
        super("没有找到动物异常："+message);
    }
}
