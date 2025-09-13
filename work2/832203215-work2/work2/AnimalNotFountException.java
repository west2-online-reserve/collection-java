package work2;

public class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException() {
    }

    public AnimalNotFountException(String message) {
        super(message);
    }
}
