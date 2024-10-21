package com.petshop;

public abstract class Animal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price;
    protected double weight;
    protected String species;

    public Animal(String name, int age, String gender, double price, double weight) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.weight = weight;
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


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    public String getSpecies() {
        return species;
    }
}
