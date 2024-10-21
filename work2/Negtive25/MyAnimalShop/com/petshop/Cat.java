package com.petshop;

public class Cat extends Animal {
    public Cat(String name, int age, String gender, double price, double weight) {
        super(name, age, gender, price, weight);
        species = "Cat";
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cat name: ").append(name).append(", age=").append(age).append(", gender=")
                .append(gender).append(", price=").append(price).append(", weight=").append(weight);

        return sb.toString();
    }
}
