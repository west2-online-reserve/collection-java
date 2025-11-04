package com.peanut.Exception;

import com.peanut.constant.ANIMALSHOP;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException(String message) {
        System.out.println(message);
        System.out.println(ANIMALSHOP.DIVIDER);
    }
}
