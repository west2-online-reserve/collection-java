package com.WestTwo.work2;

public class Hamster extends Animal {

    public Hamster() {
        this.price = price;
    }

    public Hamster(String name, int age, boolean sex, double price) {
        super(name, age, sex, price);
    }

    @Override
    public String toString() {
        return "CangShu{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", age=" + age +
                ", sex=" + sex +
                '}';
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
