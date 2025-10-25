package com.petshop.entity;

class Cat extends Animal {
    public Cat(String name, int age, char sex) {
        super(name, age, sex, 200.0);
    }

    @Override
    public String toString() {
        return "猫猫" + " 动物名：" +name + " 年龄：" + age + " 性别：" + sex + " 价格：" + price;
    }
}

