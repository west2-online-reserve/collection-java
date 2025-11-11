package com.west2online.petshop;

public class Cat extends Animal {
    public Cat() {

    }

    public Cat(String name, int age, String gender, String color) {
        super(name, age, gender, color, 200.0);
    }

    @Override
    public String toString() {
        return (
                "猫的姓名是:" + getName() +
                        " 年龄为:" + getAge() +
                        " 性别为" + getGender() +
                        " 价格为" + getPrice() +
                        " 颜色为" + getColor()
        );
    }
}
