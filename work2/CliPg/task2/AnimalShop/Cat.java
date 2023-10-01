package com.PeanutJava.task2;

public class Cat extends Animal{

    public Cat() {
    }

    public Cat(String name, int age, String gender) {
        super("猫",name,age,gender,200);
    }


    @Override
    public String toString() {
        return "Cat{" +
                "name=" + getName() + '\'' +
                ", age=" + getAge() +
                ", gender='" + getGender() + '\'' +
                ", price=" + getPrice() +
                '}';
    }

}
