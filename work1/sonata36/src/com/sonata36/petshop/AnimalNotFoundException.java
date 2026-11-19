package com.sonata36.petshop;

/** 店内没有动物可出售。 */
public final class AnimalNotFoundException extends AnimalNotFountException {
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
