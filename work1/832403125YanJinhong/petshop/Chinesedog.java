package com.YJH.west2.q.chongwudianself;

import java.util.Random;

public class Chinesedog extends Animal {

    protected boolean isVaccineInjected;

    private static final double buyingprice = 40;

    private static final double sellingprice = 100;

    public Chinesedog() {}

    public Chinesedog(String name, int age, int sex,boolean isVaccineInjected) {
        super(name, age, sex, buyingprice,sellingprice);
        this.isVaccineInjected = isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseRuralDog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + sex + '\'' +
                ", isVaccineInjected=" + isVaccineInjected +
                ", buyingPrice=" + buyingprice +
                ", sellingPrice=" + sellingprice +
                '}';
    }
}
