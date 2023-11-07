public class AnimalNotFoundException extends RuntimeException {
    protected String message;

    public AnimalNotFoundException(String message) {
        super(message);
        this.message=message;
    }
}
