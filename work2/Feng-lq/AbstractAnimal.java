package work2;

/**
 * @author FAN
 */
public abstract class AbstractAnimal {
    protected String name;
    protected int age;
    protected int gender;
    protected double price;

    public AbstractAnimal(String name, int age, int gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    @Override
    public abstract String toString();
}
