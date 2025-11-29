package com.cai.task01;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException() {
        super("店内没有动物可买");
    }

    public AnimalNotFoundException(String message) {
        super(message);
    }
}
