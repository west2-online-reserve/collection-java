package com.github.lpx.model.pet;

public class Cat extends Animal {
    public Cat(String name, int age, String sex, Double price, String color) {
        super(name, age, sex, price, color);
    }

    @Override
    public String toString() {
        return "小猫" + getName() + " 颜色:" + getColor() + " 年龄:" + getAge() + " 性别:" + getSex() + " 价格:" + getPrice();
    }

    @Override
    public void petShow() {
        System.out.println("哈基米喔南北绿豆，啊西嗨呀库奶噜~");
    }

}
