package com.sty;

public abstract class Animal {
    protected String name;
    protected int age;
    protected int gender;
    protected double price;


    @Override
    public abstract String toString();

    public Animal(String name, int age, int gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }
}
