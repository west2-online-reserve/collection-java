package org.example.Exception;

public class AnimalNotFountException extends RuntimeException {
    public AnimalNotFountException() {
        super("当前宠物店没有宠物出售。\n");
    }
}
