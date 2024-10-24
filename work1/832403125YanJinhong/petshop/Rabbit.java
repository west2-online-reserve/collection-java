package com.YJH.west2.q.chongwudianself;

public class Rabbit extends Animal{
    private static final double buyingprice = 500;

    private static final double sellingprice = 600;

    public Rabbit(String name, int age,int sex) {
        super(name, age, sex, buyingprice, sellingprice);
    }



    @Override
    public String toString() {
        return "Rabbit{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + sex + '\'' +
                ", buyingPrice=" + buyingprice +
                ", sellingPrice=" + sellingprice +
                '}';
    }
}
