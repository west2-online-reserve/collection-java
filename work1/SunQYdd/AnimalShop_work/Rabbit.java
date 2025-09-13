public class Rabbit extends Animal{

    public Rabbit(String name, int age, char sex, double price) {
        super(name, age, sex, price);
    }

    @Override
    public String toString() {
        return "Rabbit [name=" + getName() + ", age=" + getAge() + ", sex=" + getSex() + ", price=" + getPrice() +
                "元]";
    }
}
