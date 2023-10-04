package com.huayu.work02;

public class Dog extends Animal {
    boolean isVaccineInjected;

    public Dog(String animalName, int animalAge, String animalSex, double animalPrice, double animalImportPrice, boolean isVaccineInjected) {
        super(animalName, animalAge, animalSex, animalPrice, animalImportPrice);
        this.isVaccineInjected = isVaccineInjected;
        this.animalAge = animalAge;
        this.animalSex = animalSex;
        this.animalPrice = animalPrice;
        this.animalImportPrice = animalImportPrice;
        this.animalName = animalName;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "isVaccineInjected=" + isVaccineInjected +
                ", animalName='" + animalName + '\'' +
                ", animalAge=" + animalAge +
                ", animalSex='" + animalSex + '\'' +
                ", animalPrice=" + animalPrice +
                ", animalInportPrice=" + animalImportPrice +
                '}';
    }
}

