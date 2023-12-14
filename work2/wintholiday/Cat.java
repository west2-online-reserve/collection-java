package work2;

public class Cat extends Animal{
    private static final double PRICE = 200.0;

    public Cat(String name, int age, String gender) {
        super(name, age, gender, PRICE);
    }

    @Override
    public String toString() {
        return "Cat: " + getName() +
                ", Age: " + getAge() +
                ", Gender: " + getGender() +
                ", Price: " + getPrice();
    }
}
