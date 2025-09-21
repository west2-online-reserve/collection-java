package com.cyx.Exception;

public class AnimalNotFoundException extends RuntimeException {
    public AnimalNotFoundException() {
        super("购买失败，当前宠物店没有可购买的动物");
    }
    public AnimalNotFoundException(String message) {
        super(message);
    }
}
