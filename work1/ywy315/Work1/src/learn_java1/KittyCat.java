package learn_java1;

public class KittyCat extends Animal {
    final String race = "凯特猫";

    public KittyCat(String name, int age, char gender, double price, double purchasePrice) {
        super(name, age, gender, price, purchasePrice);
    }

    @Override
    public String toString() {
        return age + "岁的" + gender + "凯特猫叫做" + name + "，价格为" + price;
    }


}
