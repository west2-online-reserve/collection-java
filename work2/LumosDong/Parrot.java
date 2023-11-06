package com.dong.westtwowork;

public class Parrot extends AbstractAnimal {
    public Parrot() {}

    public Parrot(String name, int age, String gender, double price) {
        super(name, age, gender, 10000);
    }

    @Override
    public String toString() {
        return "宠物种类：" + "Parrot" + '\n' +
                "[" + "name: " + name + " " +
                "age: " + age + " " +
                "gender: " + gender + " " +
                "price: " + price + "]";
    }
}
