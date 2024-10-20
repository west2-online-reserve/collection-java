package com.petstore;

abstract class Animal {
    protected String AnimalName;
    protected int AnimalAge;
    protected String AnimalSex;
    protected double AnimalPrice;

    public Animal() {
    }

    public Animal(String animalName, int animalAge, String animalSex) {
        AnimalName = animalName;
        AnimalAge = animalAge;
        AnimalSex = animalSex;
    }

    @Override
    public abstract String toString();
}
class Cat extends Animal{

    public Cat(String animalName, int animalAge, String animalSex) {
        super(animalName, animalAge, animalSex);
        this.AnimalPrice = 200.0;
    }
    @Override
    public String toString(){
        return String.format("动物名: %s, 年龄: %d, 性别: %s, 价格: %.2f",
                this.AnimalName,this.AnimalAge,this.AnimalSex,this.AnimalPrice);
    }
}
class ChineseGardenDog extends Animal{
    private boolean isVaccineInjected; // 是否注射狂犬病疫苗
    public ChineseGardenDog(String animalName, int animalAge, String animalSex, boolean isVaccineInjected) {
        super(animalName, animalAge, animalSex);
        this.AnimalPrice = 100.0;
        this.isVaccineInjected = isVaccineInjected;
    }
    @Override
    public String toString(){
        return String.format("动物名: %s, 年龄: %d, 性别: %s, 价格: %.2f",
                this.AnimalName,this.AnimalAge,this.AnimalSex,this.AnimalPrice,this.isVaccineInjected ? "是" : "否");
    }
}
class Labrador extends Animal{
    public Labrador(String animalName, int animalAge, String animalSex) {
        super(animalName, animalAge, animalSex);
        this.AnimalPrice = 150.0;
    }
    @Override
    public String toString(){
        return String.format("动物名: %s, 年龄: %d, 性别: %s, 价格: %.2f",
                this.AnimalName,this.AnimalAge,this.AnimalSex,this.AnimalPrice);
    }
}