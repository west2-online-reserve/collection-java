package com.huayu.work02;

public abstract class Animal {
    String animalName;
    int animalAge;
    String animalSex;
    double animalPrice;
    double animalImportPrice;

    public Animal(String animalName, int animalAge, String animalSex, double animalPrice, double animalImportPrice) {
    }

    @Override
    public abstract String toString();


}
