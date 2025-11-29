package com.animalshop.model;

public class Cat extends Animal {
    public Cat(String name, int age, Gender sex){
        super(name, age, sex, 200);
    }
    @Override
    public String toString(){
        return String.format("猫猫 [名字: %s, 年龄: %d, 性别: %s, 价格: %.2f]", name, age, sex, price);
    }
}
