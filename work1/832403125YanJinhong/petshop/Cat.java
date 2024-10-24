package com.YJH.west2.q.chongwudianself;

public class Cat extends Animal{

    private static final double buyingprice = 100;

    private static final double sellingprice = 200;

    public Cat() {}

    public Cat(String name, int age, int sex) {
        super(name, age, sex, buyingprice,sellingprice);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + sex + '\'' +
                ", buyingPrice=" + buyingprice +
                ", sellingPrice=" + sellingprice +
                '}';
    }
}
