package com.peanut;

public abstract class Animal {

    protected String name;
    protected int age;

    // 0 表示公， 1表示母
    protected boolean sex;

    protected double price;

    public Animal(String name, int age, boolean sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    @Override
    public abstract String toString();

    public double getPrice() {
        return price;
    }
}
