package learn_java1;

public class Dinosaur extends Animal {
    final String race = "恐龙";

    public Dinosaur(String name, int age, char gender, double price, double purchasePrice) {
        super(name, age, gender, price, purchasePrice);
    }

    @Override
    public String toString() {
        return age + "岁的" + gender + "恐龙叫做" + name + "，价格为" + price;
    }

}
