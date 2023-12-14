package com.west2.javawork2;

//变量:
//动物名(String)
//年龄(int)
//性别
//价格(double)
//....
//方法
//一个全参构造方法
//一个抽象的toString() 方法
//........


public abstract class Animal {
    private String name;
    private int age;
    private String sex;
    protected double price;

    public Animal(String name,int age,String sex,double price){
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.price = price;
    }

    public abstract String toString();

    //get与set

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
