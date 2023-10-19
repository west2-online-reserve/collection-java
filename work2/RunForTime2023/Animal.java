public abstract class Animal {
    protected String name;
    protected int age;
    protected char sex;
    protected double price;

    Animal(String name, int age, char sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    Animal() {
    }

    @Override
    public abstract String toString();
}
