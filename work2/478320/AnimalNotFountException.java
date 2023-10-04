package com.huayu.work02;

public class AnimalNotFountException extends RuntimeException {

    public AnimalNotFountException() {

    }

    @Override
    public String toString() {
        return "宠物店内没有宠物";
    }
}
