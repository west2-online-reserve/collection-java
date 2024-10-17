package exception;

public class AnimalNotFountException extends RuntimeException{
    public AnimalNotFountException(String msg){
        super(msg);
    }

    public AnimalNotFountException() {
    }
}
