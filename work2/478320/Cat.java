package com.huayu.work02;

public class Cat extends Animal {


    public Cat(String animalName, int animalAge, String animalSex, double animalPrice, double animalImportPrice) {
        super(animalName, animalAge, animalSex, animalPrice, animalImportPrice);

        this.animalAge = animalAge;
        this.animalSex = animalSex;
        this.animalPrice = animalPrice;
        this.animalImportPrice = animalImportPrice;
        this.animalName = animalName;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "animalName='" + animalName + '\'' +
                ", animalAge=" + animalAge +
                ", animalSex='" + animalSex + '\'' +
                ", animalPrice=" + animalPrice +
                ", animalImportPrice=" + animalImportPrice +
                '}';
    }
}
