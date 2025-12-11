package com.Anna_west2_work01.animalShop.animals;

import com.Anna_west2_work01.animalShop.Animal;

public class CRDogs extends Animal {
    private boolean isVaccineInjected;

    public CRDogs() {}

    public CRDogs(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    public CRDogs(String name, int age, String gender, double inPrice, double outPrice, boolean isVaccineInjected) {
        super(name, age, gender, inPrice, outPrice);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseDogs : " +
                "name = " + name + "\n" +
                "age = " + age + "\n" +
                "gender = " + gender + "\n" +
                "isVaccineInjected = " + isVaccineInjected + "\n" +
                "inPrice = " + inPrice + "\n" +
                "outPrice = " + outPrice + "\n";
    }


}

