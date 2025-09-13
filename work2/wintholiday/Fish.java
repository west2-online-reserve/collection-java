package work2;

public class Fish extends Animal{
    private static final double PRICE = 30.0;

    public Fish(String name, int age, String gender) {
        super(name, age, gender, PRICE);
    }

    @Override
    public String toString() {
        return "Fish " + getName() +
                ", Age: " + getAge() +
                ", Gender: " + getGender() +
                ", Price: " + getPrice();
    }
}
