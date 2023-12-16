package Xuxiiii;

public class Pig extends Animal{
    public Pig(String name, int age, String sex, double price) {
        super(name, age, sex, price);
    }

    @Override
    public String toString() {
        return "Pig"+'\n'+
                "name is "+name+
                "age is "+age+
                "sex is "+sex+
                "price is "+price;
    }
}
