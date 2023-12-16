package com.sty;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message) {
        super(message);
    }
}