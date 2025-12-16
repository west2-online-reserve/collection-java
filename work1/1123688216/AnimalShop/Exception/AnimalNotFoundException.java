package Exception;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message) {
        super(message);
    }
    public AnimalNotFoundException() {
        super("店里目前没有宠物了，请下次再来");
    }
}
