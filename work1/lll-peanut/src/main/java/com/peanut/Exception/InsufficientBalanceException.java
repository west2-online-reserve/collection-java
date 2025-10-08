package com.peanut.Exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        System.out.println(message);
    }
}
