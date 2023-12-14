package com.west2.javawork2;

public class Mouse extends Animal{
    public Mouse(String name, int age, String sex, double price) {
        super(name, age, sex, 10);
    }

    @Override
    public String toString() {
        return  "鼠子:" + getName() +
                ",年龄:" + getAge() +
                ",性别:" + getSex() +
                ",价格:" + getPrice();
    }
}
