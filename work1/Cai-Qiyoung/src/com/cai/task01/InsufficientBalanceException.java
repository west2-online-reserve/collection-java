package com.cai.task01;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("余额不足，无法买入动物");
    }

    public InsufficientBalanceException(String message) {
        super(message);
    }
}
