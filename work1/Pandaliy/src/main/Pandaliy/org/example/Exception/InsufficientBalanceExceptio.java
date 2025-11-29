package org.example.Exception;

public class InsufficientBalanceExceptio extends RuntimeException {
    public InsufficientBalanceExceptio() {
        super("余额不足\n");
    }
}
