package work2;

public abstract class Animal {
    protected String name;
    protected int age;
    protected char gender;
    protected double Price;

    public Animal(String name, int age, char gender, double Price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.Price = Price;
    }

    public abstract String toString();
}
