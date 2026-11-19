package com.sonata36.petshop;

/** 余额不足以买入动物。 */
public final class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
