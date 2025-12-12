package com.Anna_west2_work01.animalShop;

public abstract class Animal {
    protected String name;
    protected int age;
    protected String gender;
    protected double inPrice;
    protected double outPrice;

    public Animal() {}

    public Animal(String name, int age, String gender, double inPrice, double outPrice) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.inPrice = inPrice;
        this.outPrice = outPrice;
    }

    public abstract String toString();

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

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(double outPrice) {
        this.outPrice = outPrice;
    }
}
