package Eyrine;

public class Rabbit extends Animal{

    public Rabbit(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }

    @Override
    public String toString() {
        return "兔子 "+getName()+",价格：" +getPrice();
    }
}
