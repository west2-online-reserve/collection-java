package java_work2;

/**
 * Animal类
 *
 * @author formrc
 * @date 2023/10/23
 */
abstract class Animal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price;

    public Animal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    public Animal() {
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double getPrice() {
        return price;
    }

    public abstract String toString();
}
