package com.catowl.animalshop;

public class Cat extends Animal {
    private final int PURCHASE_PRICE = 100;

    public Cat(String name, int age, String gender) {
        super(name, age, gender);
        setPrice(200);
    }

    public int getPURCHASE_PRICE() {
        return PURCHASE_PRICE;
    }

    @Override
    public String toString() {
        return "品种：猫猫 名字：" + getName() + " 年龄：" + getAge() + " 性别：" + getGender() + " 价格：" + getPrice();
    }
}
