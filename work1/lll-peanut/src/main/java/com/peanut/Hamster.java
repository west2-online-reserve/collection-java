package com.peanut;

public class Hamster extends Animal {

    String color;

    public Hamster(String name, int age, boolean sex, double price, String color) {
        super(name, age, sex, price);
        this.color = color;
    }

    @Override
    public String toString() {
        return "{Hamster name:" + name + " age:" + age + " price:" +
                price + " sex:" + sex + " color:" + color + "}";
    }
}
