package com.cyx.Amimals;

public abstract class Animal {
    // 抽象类的成员变量均为protected
    protected String name;    // 名称
    protected int age;           // 年龄
    protected String gender;  //  性别
    protected double purchasePrice; // 买入价（成本价）
    protected double sellingPrice;  // 卖出价（售价）
    // 全参构造
    public Animal(String name, int age, String gender, double purchasePrice, double sellingPrice) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public abstract String toString();
}
