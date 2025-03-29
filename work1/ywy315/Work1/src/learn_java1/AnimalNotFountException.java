package learn_java1;

public class AnimalNotFountException extends RuntimeException {
    String message;

    public AnimalNotFountException() {
        this.message = "店内没有动物可买";
    }

    void get_Message() {
        System.out.println(message);
    }
}
