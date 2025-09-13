package com.sty;

public class Cat extends Animal{

    public Cat(String name, int age, int gender, double price) {
        super(name, age, gender, 200);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", price=" + price +
                '}';
    }

}
