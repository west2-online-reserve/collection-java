package com.YJH.west2.q.chongwudianself;

public class Cat extends Animal{

    private static final double BYINGPRICE = 100;

    private static final double SELLINGPRICE = 200;

    public Cat() {}

    public Cat(String name, int age, int sex) {
        super(name, age, sex, BYINGPRICE,SELLINGPRICE);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + sex + '\'' +
                ", buyingPrice=" + BYINGPRICE +
                ", sellingPrice=" + SELLINGPRICE +
                '}';
    }
}
