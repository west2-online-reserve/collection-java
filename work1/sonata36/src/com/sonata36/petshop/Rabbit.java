package com.sonata36.petshop;

import java.util.Locale;

/** 兔子，售价固定为 80 元。 */
public final class Rabbit extends Animal {
    public static final double PRICE = 80.0;

    public Rabbit(String name, int age, String sex) {
        super(name, age, sex, PRICE);
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "兔子{name='%s', age=%d, sex='%s', price=%.2f}",
                getName(), getAge(), getSex(), getPrice());
    }
}
