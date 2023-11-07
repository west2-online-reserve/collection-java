package com.WestTwo.work2;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException() {
    }

    public AnimalNotFoundException(String message) {
        super(message);
    }
}