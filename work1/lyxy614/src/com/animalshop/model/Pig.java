package com.animalshop.model;

public class Pig extends Animal {
    public Pig(String name, int age, Gender sex){
        super(name, age, sex, 200);
    }
    @Override
    public String toString(){
        return String.format("小猪 [名字: %s, 年龄: %d, 性别: %s, 价格: %.2f]", name, age, sex, price);
    }
}
