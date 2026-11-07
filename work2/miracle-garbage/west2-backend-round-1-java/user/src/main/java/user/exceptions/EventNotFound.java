package user.exceptions;

public class EventNotFound extends RuntimeException {
    public EventNotFound(String msg) {
        super(msg);
    }
}
