package myshop.animals;

import java.util.Scanner;

public abstract class Animal {
    protected String name;//动物名
    protected int age;//年龄
    protected String sex;//性别
    protected double price;//价格
    Scanner scanner = new Scanner(System.in);

    public Animal() {
    }

    public Animal(String name, int age, String sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    //抽象toString
    public abstract String toString();
}

