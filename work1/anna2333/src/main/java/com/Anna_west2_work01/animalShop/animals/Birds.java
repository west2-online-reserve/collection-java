package com.Anna_west2_work01.animalShop.animals;

import com.Anna_west2_work01.animalShop.Animal;

public class Birds extends Animal {

    public Birds() {}

    public Birds(String name, int age, String gender, double inPrice, double outPrice) {
        super(name, age, gender, inPrice, outPrice);
    }

    @Override
    public String toString() {
        return "Birds : " +
                "name = " + name + "\n" +
                "age = " + age + "\n" +
                "gender = " + gender + "\n" +
                "inPrice = " + inPrice + "\n" +
                "outPrice = " + outPrice + "\n";
        }
}


