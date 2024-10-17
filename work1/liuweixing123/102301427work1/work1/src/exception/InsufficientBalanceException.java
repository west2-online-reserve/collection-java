package exception;

import jdk.jshell.spi.ExecutionControl;

public class InsufficientBalanceException extends RuntimeException {


    public InsufficientBalanceException(String msg) {
        super(msg);
    }

    public InsufficientBalanceException() {
    }
}
