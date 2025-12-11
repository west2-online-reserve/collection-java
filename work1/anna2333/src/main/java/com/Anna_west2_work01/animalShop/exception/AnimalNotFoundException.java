package com.Anna_west2_work01.animalShop.exception;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException() {}
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
