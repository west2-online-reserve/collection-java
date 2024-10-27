package com.animals;

public class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String massage){
        super(massage);
    }
}

