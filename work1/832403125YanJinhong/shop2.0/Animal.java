package com.YJH.west2.q.chongwudianself;

public abstract class Animal {
    protected String name;
    protected int age;
    protected int sex;  //"female"==0/"male"==1
    protected double buyingprice;;
    protected double sellingprice;;

    public Animal() {}

    public Animal(String name, int age, int sex, double buyingPrice,double sellingprice) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.buyingprice = buyingPrice;
        this.sellingprice = sellingprice;
    }

    public abstract String toString() ;





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public double getBuyingprice() {
        return buyingprice;
    }

    public void setBuyingprice(double buyingprice) {
        this.buyingprice = buyingprice;
    }

    public double getSellingprice() {
        return sellingprice;
    }

    public void setSellingprice(double sellingprice) {
        this.sellingprice = sellingprice;
    }
}
