package com.peanut;

import com.peanut.constant.PRICE;

public class ChineseRuralDog extends Animal {

    private boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, boolean sex, boolean isVaccineInjected) {
        super(name, age, sex, PRICE.DOG_PRICE);
        price = 100;
    }

    @Override
    public String toString() {
        return "{ChineseRuralDog: " + " name:" + name + " age:" + age + " price:" +
                price + " isVaccineInjected:" + isVaccineInjected
                + " sex:" + sex + "}";
    }
}
