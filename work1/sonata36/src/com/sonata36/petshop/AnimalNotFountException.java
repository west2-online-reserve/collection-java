package com.sonata36.petshop;

/** 兼容题目第 8 条中的拼写，业务代码请使用 AnimalNotFoundException。 */
public class AnimalNotFountException extends RuntimeException {
    public AnimalNotFountException(String message) {
        super(message);
    }
}
