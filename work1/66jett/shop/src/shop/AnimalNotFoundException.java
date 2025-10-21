package shop;

/**
 * 动物未找到异常
 */
public class AnimalNotFoundException extends RuntimeException {

    public AnimalNotFoundException(String message) {
        super(message);
    }
}