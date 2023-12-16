package com.west2.check02;

/**
 * @author yuyu
 */
public class AnimalNotFoundException extends RuntimeException {
    static final long serialVersionUID = -703489716868766939L;

    public AnimalNotFoundException() {
        super();
    }

    public AnimalNotFoundException(String message) {
        super(message);
    }

    public AnimalNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AnimalNotFoundException(Throwable cause) {
        super(cause);
    }

    public AnimalNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
