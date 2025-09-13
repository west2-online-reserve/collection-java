public abstract class Animal {

    private String name;
    private int age;
    private char sex;
    private double price;

    public Animal(String name, int age, char sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public double getPrice() {
        return price;
    }

    public abstract String toString();
}
