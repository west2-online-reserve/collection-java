package com.west2.work2;

public abstract class Animal {
    protected String name;
    protected int old;
    protected char gender;
    protected double price;

    public Animal(String name, int old, char gender, double price) {
        this.name = name;
        this.old = old;
        this.gender = gender;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOld() {
        return old;
    }

    public void setOld(int old) {
        this.old = old;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String toString();
}


