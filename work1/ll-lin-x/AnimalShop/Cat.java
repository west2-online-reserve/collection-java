package west2.task1.AnimalShop;

public class Cat extends Animal{
    public Cat(String name, int age, int gender, double price) {
        super(name, age, gender, price);
    }
    @Override
    public String toString(){
        return "Cat";
    }
}
