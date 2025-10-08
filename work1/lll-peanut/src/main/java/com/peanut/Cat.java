package com.peanut;

import com.peanut.constant.PRICE;

public class Cat extends Animal {

    public Cat(String name, int age, boolean sex) {
        super(name, age, sex, PRICE.CAT_PRICE);
    }

    @Override
    public String toString() {
        return "{Cat :" + " name:" + name + " age:" + age + " price:" +
                price + " sex:" + sex + "}";
    }
}
