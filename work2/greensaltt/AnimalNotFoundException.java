package animalshop;

/**
 *店内没有动物可买时抛出无法找到动物异常
 */
public class AnimalNotFoundException extends RuntimeException{

    /**
     * 无参构造
     */
    public AnimalNotFoundException() {}

    /**
     * 有参构造
     */
    public AnimalNotFoundException(String message) {
        super(message);
    }

}
