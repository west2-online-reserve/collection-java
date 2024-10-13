package com.catowl.animalshop;

public abstract class Animal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price;
    protected final int PURCHASE_PRICE=0;

    public Animal(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public Animal() {
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

    public double getPrice() {
        return price;
    }

    public int getPURCHASE_PRICE() {
        return PURCHASE_PRICE;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
