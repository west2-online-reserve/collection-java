package com.cyx.Exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException() {
        super("对不起，余额不足！");
    }
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
