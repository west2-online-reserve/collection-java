package Eyrine;

public class Cat extends Animal{


    public Cat(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }

    @Override
    public String toString() {
        return "小猫 "+getName()+",价格：" +getPrice();
    }
}
