package com.Anna_west2_work01.animalShop.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {}
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
