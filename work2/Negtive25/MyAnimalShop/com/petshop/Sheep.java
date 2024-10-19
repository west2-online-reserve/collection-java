package com.petshop;

public class Sheep extends Animal {
    public Sheep(String name, int age, String gender, double price, double weight) {
        super(name, age, gender, price, weight);
        species = "Sheep";
    }
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Sheep name: ").append(name).append(", age=").append(age).append(", gender=")
                .append(gender).append(", price=").append(price).append(", weight=").append(weight);

        return sb.toString();
    }
}
