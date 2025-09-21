package com.cyx.Amimals;

public class Bird extends Animal{
    private final double purchasePrice = 100;// 鸟价格
    private final double sellingPrice = 120;// 鸟成本价格
    public String species; // 鸟种类

    public Bird(String name, int age, String gender,String species) {
        super(name, age, gender, 100,120);
        this.species = species;
    }

    @Override
    public double getPurchasePrice() {
        return purchasePrice;
    }

    @Override
    public double getSellingPrice() {
        return sellingPrice;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "该鸟的名字是： " + getName() + "，年龄是：" +
                getAge() + "，性别是：" + getGender() + "，购买价格是：" +
                getPurchasePrice() + "，出售价格是：" + getSellingPrice() + "，种类是：" + species;
    }
}
