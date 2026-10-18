package oop.PetShop;

public class Dog extends Animal{
    boolean isVaccineInjected;
    static int price = 100;
    public Dog() {;
    }
    public Dog(String name, int age, String gender,boolean isVaccineInjected) {
        super(name, age, gender);
        this.isVaccineInjected = isVaccineInjected;
    }
    public int getPrice() {
        return price;
    }
    @Override
    public String toString() {
        return name + ", " + age + ", " + gender + ", " + isVaccineInjected;
    }
}
