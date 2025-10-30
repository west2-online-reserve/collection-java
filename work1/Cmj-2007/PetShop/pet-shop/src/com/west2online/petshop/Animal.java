package com.west2online.petshop;

public abstract class Animal {

    protected String name;
    protected int age;
    protected String gender;
    protected double price;
    protected String color;

    public Animal() {

    }

    public Animal(String name, int age, String gender,String color,double price) {

        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.color = color;

    }

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

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }

    @Override
    public abstract String toString();

}
