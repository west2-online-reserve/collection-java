package com.petshop.entity;

abstract class Animal {
    protected String type;
    protected String name;
    protected int age;
    protected final char sex;
    protected double price;

    public Animal(String type, String name, int age, char sex, double price) {
        this.type = type;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return this.sex;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String toString();
}