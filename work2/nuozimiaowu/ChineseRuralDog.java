package com.sty;

public class ChineseRuralDog extends Animal{

    private boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, int gender,boolean IsVaccineInjected)
    {
        super(name, age, gender,100);
        this.isVaccineInjected = IsVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseRuralDog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", price=" + price +
                '}';
    }

}
