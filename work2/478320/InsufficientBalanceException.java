package com.huayu.work02;

public class InsufficientBalanceException extends RuntimeException {


    public InsufficientBalanceException() {

    }

    @Override
    public String toString() {
        return "余额不足";
    }
}
