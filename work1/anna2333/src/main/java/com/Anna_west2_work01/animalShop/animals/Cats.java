package com.Anna_west2_work01.animalShop.animals;

import com.Anna_west2_work01.animalShop.Animal;

public class Cats extends Animal {

    public Cats() {}

    public Cats(String name, int age, String gender, double inPrice, double outPrice) {
        super(name, age, gender, inPrice, outPrice);
    }

    @Override
    public String toString() {
        return "Cats : " +
                "name = " + name + "\n" +
                "age = " + age + "\n" +
                "gender = " + gender + "\n" +
                "inPrice = " + inPrice + "\n" +
                "outPrice = " + outPrice + "\n";
    }
}

