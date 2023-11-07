package PetShop;

/**
 * @author Jst599
 * @date 2023/10/18
 */
public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
