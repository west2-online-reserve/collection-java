package com.github.lpx.model.pet;

public class Dragon extends Animal {
    public Dragon(String name, int age, String sex, Double price, String color) {
        super(name, age, sex, price, color);
    }

    @Override
    public String toString() {
        return "龙" + getName() + " 颜色:" + getColor() + " 年龄:" + getAge() + " 性别:" + getSex() + " 价格:" + getPrice();
    }

    @Override
    public void petShow() {
        System.out.println("嗷呜~，恶龙咆哮");
    }
}
