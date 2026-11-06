package com.west2.dwasearch.io;

public class DataException extends Exception {
    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
