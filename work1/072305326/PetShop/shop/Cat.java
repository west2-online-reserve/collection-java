package com.cyx.Amimals;

public class Cat extends Animal{
    private final double purchasePrice = 150; // 猫咪购买价格
    private final double sellingPrice = 200; // 猫咪出售价格

    public Cat(String name, int age, String gender) {
        super(name, age, gender,150,200);
    }

    @Override
    public double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public double getSellingPrice() {
        return sellingPrice;
    }

    @Override
    public String toString() {
        return "猫咪"
                + "，名字为：" + getName()
                + "，年龄为：" + getAge()
                + "，性别为：" + getGender()
                + "，购买价格为：" + getPurchasePrice()
                + "，出售价格为：" + getSellingPrice();
    }
}
