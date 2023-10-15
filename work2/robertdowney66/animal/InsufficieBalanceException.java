package com.west2.check02;

/**
 * @author yuyu
 */
public class InsufficieBalanceException extends RuntimeException {
    public InsufficieBalanceException() {
        super();
    }

    public InsufficieBalanceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public InsufficieBalanceException(Throwable cause) {
        super(cause);
    }

    public InsufficieBalanceException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsufficieBalanceException(String message) {
        super(message);
    }
}
