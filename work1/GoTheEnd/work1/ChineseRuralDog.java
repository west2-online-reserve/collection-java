package com.java.west2.work2.shop;

public class ChineseRuralDog extends Animal {
    protected boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, String gender, double price, String temperament, boolean isVaccineInjected) {
        super(name, age, gender, price, temperament);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public void feed(){
        System.out.println("woof!");
    }
    @Override
    public String toString(){
        return "ChineseRuralDog" + "\n" +
                "-----------" +
                "name: " + name + "\n" +
                "age: " + age + "\n" +
                "gender: " + gender + "\n" +
                "price: " + price + "\n" +
                "temperament: " + temperament + "\n" +
                "isVaccineInjected: " + isVaccineInjected;
    }
}
