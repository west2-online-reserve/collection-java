package com.west2.work2;

public class Cat extends Animal {
    public Cat(String name, int age, double price, String sex) {
        super(name, age, price, sex);
    }

    public String toString() {
        return "这个名叫" + name + "性别是" + sex + "的小猫，" +
                "今年" + age + "岁了噢！它的价格是" + price + "元。欢迎把它带回家！";
    }

    public String toString1() {
        return "[" + name + "," + sex + "," + age + "," + price + "]";
    }
}
