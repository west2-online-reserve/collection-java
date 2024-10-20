package com.catowl.animalshop;

public class Rabbit extends Animal {
    private final int PURCHASE_PRICE = 25;

    public Rabbit(String name, int age, String gender) {
        super(name, age, gender);
        setPrice(50);
    }

    public int getPURCHASE_PRICE() {
        return PURCHASE_PRICE;
    }

    @Override
    public String toString() {
        return "品种：兔兔 名字：" + getName() + " 年龄：" + getAge() + " 性别：" + getGender() + " 价格：" + getPrice();
    }
}
