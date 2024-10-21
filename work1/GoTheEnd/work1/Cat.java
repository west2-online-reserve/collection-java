package com.java.west2.work2.shop;

public class Cat extends Animal {
    public Cat(String name, int age, String gender, double price, String temperament) {
        super(name, age, gender, price, temperament);
    }

    @Override
    public String toString(){
        return "Cat" + "\n" +
                "-----------" +
                "name: " + name + "\n" +
                "age: " + age + "\n" +
                "gender: " + gender + "\n" +
                "price: " + price + "\n" +
                "temperament: " + temperament;
    }

    @Override
    public void feed(){
        System.out.println("mew!!");
    }
}
