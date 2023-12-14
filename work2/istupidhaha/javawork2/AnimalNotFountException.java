package com.west2.javawork2;

public class AnimalNotFountException extends RuntimeException{
    //店内未找到宠物
    private Animal animal;

    public Animal getAnimal() {
        return animal;
    }

    public AnimalNotFountException(String warning){
        super(warning);
    }
}
