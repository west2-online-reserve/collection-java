package main.java.exception;

public class CommandLineArgsException extends RuntimeException {
    public CommandLineArgsException(String message) {
        super(message);
    }
}
