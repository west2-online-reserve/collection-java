package learn_java1;

public class AnimalNotFountException extends RuntimeException {
    private final String message;

    public AnimalNotFountException() {
        this.message = "店内没有动物可买";
    }

    public void get_Message() {
        System.out.println(message);
    }
}
