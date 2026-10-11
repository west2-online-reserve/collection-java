package com.sonata36.petshop;

import java.util.Locale;

/** 猫，售价固定为 200 元。 */
public final class Cat extends Animal {
    public static final double PRICE = 200.0;

    public Cat(String name, int age, String sex) {
        super(name, age, sex, PRICE);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "猫{name='%s', age=%d, sex='%s', price=%.2f}",
                getName(), getAge(), getSex(), getPrice());
    }
}
