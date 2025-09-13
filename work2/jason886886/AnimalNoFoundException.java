package work2;

/**
 * @author jason
 */
public class AnimalNoFoundException extends RuntimeException {
    public AnimalNoFoundException() {
    }

    public AnimalNoFoundException(String message) {
        super(message);
    }
}
