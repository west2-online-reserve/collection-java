package Eyrine;

public class Tiger extends Animal{
    public Tiger(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }

    @Override
    public String toString() {
        return  "老虎 "+getName()+",价格：" +getPrice();
    }
}
