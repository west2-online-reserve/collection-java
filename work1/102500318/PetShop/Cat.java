package oop.PetShop;

public class Cat extends Animal{
    static int price = 200;
    public Cat(String name, int age, String gender){
        super(name, age, gender);
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name+" "+age+" "+gender;
    }
}
