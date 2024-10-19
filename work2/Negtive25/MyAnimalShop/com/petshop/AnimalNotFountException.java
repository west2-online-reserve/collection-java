package com.petshop;

public class AnimalNotFountException extends RuntimeException {
    public AnimalNotFountException() {
        super();
    }
    public AnimalNotFountException(String message) {
        super(message);
    }
}
