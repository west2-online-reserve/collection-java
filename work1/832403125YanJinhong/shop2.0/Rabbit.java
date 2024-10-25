package com.YJH.west2.q.chongwudianself;

public class Rabbit extends Animal{
    private static final double BYINGPRICE = 500;

    private static final double SELLINGPRICE = 600;

    public Rabbit(String name, int age,int sex) {
        super(name, age, sex, BYINGPRICE, SELLINGPRICE);
    }



    @Override
    public String toString() {
        return "Rabbit{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + sex + '\'' +
                ", buyingPrice=" + BYINGPRICE +
                ", sellingPrice=" + SELLINGPRICE +
                '}';
    }
}
