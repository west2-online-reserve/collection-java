package com.animalshop.model;

public abstract class Animal {
    protected String name;
    protected int age;
    protected Gender sex;
    protected double price;
    public Animal(String name, int age, Gender sex, double price){
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    public abstract String toString();
}

