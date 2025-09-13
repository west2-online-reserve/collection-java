package com.petshop;

public class Dog extends Animal {
    private boolean isVaccineInjected;

    public Dog(String name, int age, String gender, double price, double weight, boolean isVaccineInjected) {
        super(name, age, gender, price, weight);
        species = "Dog";
        this.isVaccineInjected = isVaccineInjected;
    }
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Dog name: ").append(getName()).append(", Age: ").append(getAge()).append(", Gender: ").append(getGender())
                .append(", Price: ").append(getPrice()).append(", Weight: ").append(getWeight()).append(", Vaccine Injected: ")
                .append(isVaccineInjected());

        return sb.toString();
    }
    public boolean isVaccineInjected() {
        if (isVaccineInjected) {
            return true;
        }
        else {
            return false;
        }
    }
}
