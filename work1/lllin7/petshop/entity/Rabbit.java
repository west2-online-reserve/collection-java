package com.petshop.entity;

class Rabbit extends Animal {
    public Rabbit(String name, int age, char sex) {
        super(name, age, sex, 50.0);
    }

    @Override
    public String toString() {
        return "小兔子" + " 动物名：" + name + " 年龄：" + age + " 性别：" + sex + " 价格：" + price;
    }
}
