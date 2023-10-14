public class Cat extends Animal {


    Cat(String name, int age, boolean sex, double restorePrice) {
        super(name, age, sex, 200, restorePrice);
    }

    @Override
    public String toString() {
        return "Name:" + name + "\nAge:" + age + "\nsex:" + (sex ? "boy" : "girl") + "\nPrice:" + price;
    }
}
