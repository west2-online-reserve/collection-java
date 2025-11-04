package org.example;

public abstract class Animal {
    protected String name;
    protected int age;
    protected int sex;
    protected double price;

    Animal(String n, int a, int s, double p) {
        name = n;
        age = a;
        sex = s;
        price = p;
    }

    public abstract String toString();

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSex() {
        return sex;
    }

    public double getPrice() {
        return price;
    }
}
