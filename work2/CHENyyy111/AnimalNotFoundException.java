package AnimalShop;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String msg) {
        super(msg);
    }
}
