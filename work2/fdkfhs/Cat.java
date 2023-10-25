public class Cat extends Animal {
    private double price;

    public Cat() {
    }

    public Cat(String name, int age, boolean sex) {
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", age=" + age +
                ", " + (sex ? "雄" : "雌") + "}";
    }
}