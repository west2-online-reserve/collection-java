package com.animals;
/* 一个Animal动物类 (抽象类 abstract )

变量:
动物名(String)
年龄(int)
性别
价格(double)
....
方法
一个全参构造方法
一个抽象的toString() 方法
........*/
public abstract class Animals {
    private double cost;//进价
    private String name;//宠物种类
    private int age;//年龄
    private String gender;//性别
    private double price;//售价

    public Animals(String name, int age, String gender, double price) {
        this.cost = cost;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}



