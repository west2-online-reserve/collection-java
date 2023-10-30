package com.WestTwo.work2;

public class Cat extends Animal {
    double price = 200;

    public Cat() {
    }

    public Cat(String name, int age, boolean sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Cat{" +
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