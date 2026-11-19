package com.sonata36.petshop;

import java.util.Locale;

/** 中华田园犬，售价固定为 100 元。 */
public final class ChineseRuralDog extends Animal {
    public static final double PRICE = 100.0;

    private final boolean vaccineInjected;

    public ChineseRuralDog(String name, int age, String sex, boolean vaccineInjected) {
        super(name, age, sex, PRICE);
        this.vaccineInjected = vaccineInjected;
    }

    public boolean isVaccineInjected() {
        return vaccineInjected;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT,
                "中华田园犬{name='%s', age=%d, sex='%s', price=%.2f, vaccineInjected=%s}",
                getName(), getAge(), getSex(), getPrice(), vaccineInjected);
    }
}
