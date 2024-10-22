/*当前没有动物可以购买时抛出异常*/
public class AnimalNotFountException extends RuntimeException {
    public AnimalNotFountException(String message) {
        super(message);
    }
}
