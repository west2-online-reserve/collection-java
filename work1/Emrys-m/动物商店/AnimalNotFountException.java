package HomeWork;

public class AnimalNotFountException extends RuntimeException {
    String message;
    public AnimalNotFountException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
