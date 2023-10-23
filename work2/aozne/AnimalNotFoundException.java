/**
 * @author aozne
 * @date 2023/10/23 21:12
 **/
public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}