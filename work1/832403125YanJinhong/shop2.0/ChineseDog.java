package com.YJH.west2.q.chongwudianself;

import java.util.Random;

public class ChineseDog extends Animal {

    protected boolean isVaccineInjected;

    private static final double BYINGPRICE = 40;

    private static final double SELLINGPRICE = 100;

    public ChineseDog() {}

    public ChineseDog(String name, int age, int sex,boolean isVaccineInjected) {
        super(name, age, sex, BYINGPRICE,SELLINGPRICE);
        this.isVaccineInjected = isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseDog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + sex + '\'' +
                ", isVaccineInjected=" + isVaccineInjected +
                ", buyingPrice=" + BYINGPRICE +
                ", sellingPrice=" + SELLINGPRICE +
                '}';
    }
}
