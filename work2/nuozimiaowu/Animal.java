package com.sty;

public abstract class Animal {
    String name;
    int age;
    int gender;
    double price;

    @Override
    public abstract String toString();

    public Animal(String name, int age, int gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }
}
