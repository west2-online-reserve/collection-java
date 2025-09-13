package com.dong.westtwowork;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String msg){
        super(msg);
    }
}
