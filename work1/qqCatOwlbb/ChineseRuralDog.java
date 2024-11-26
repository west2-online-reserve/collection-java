package com.catowl.animalshop;

public class ChineseRuralDog extends Animal {
    private final int PURCHASE_PRICE = 50;
    private boolean isVaccinelnjected;

    public ChineseRuralDog(String name, int age, String gender, boolean isVaccinelnjected) {
        super(name, age, gender);
        this.isVaccinelnjected = isVaccinelnjected;
        setPrice(100);
    }

    public int getPURCHASE_PRICE() {
        return PURCHASE_PRICE;
    }

    @Override
    public String toString() {
        return "品种：中华田园犬 名字：" + getName() + " 年龄：" + getAge() + " 性别：" + getGender() + " 价格：" + getPrice();
    }
}
