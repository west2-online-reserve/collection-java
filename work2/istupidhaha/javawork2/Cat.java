package com.west2.javawork2;

public class Cat extends Animal {
    public Cat(String name, int age, String sex, double price) {
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        return  "喵子:" + getName() +
                ",年龄:" + getAge() +
                ",性别:" + getSex() +
                ",价格:" + getPrice();
    }
}