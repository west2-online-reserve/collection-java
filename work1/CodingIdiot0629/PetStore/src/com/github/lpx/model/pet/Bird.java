package com.github.lpx.model.pet;

public class Bird extends Animal {
    public Bird(String name, int age, String sex, Double price, String color) {
        super(name, age, sex, price, color);
    }

    @Override
    public String toString() {
        return "鸟儿" + getName() + " 颜色:" + getColor() + " 年龄:" + getAge() + " 性别:" + getSex() + " 价格:" + getPrice();
    }

    @Override
    public void petShow() {
        System.out.println("啾啾啾");
    }
}
