package com.Eeeeye.base.work2;

public class NotOpenException extends RuntimeException{
    public NotOpenException() {
    }

    public NotOpenException(String message) {
        super(message);
    }
}
