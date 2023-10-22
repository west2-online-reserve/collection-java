import java.util.Objects;

/**
 * Date 2023/10/18  13:34
 *
 * @author Kkkrran
 * @version 1.0
 */

public abstract class Animal {
    protected String name;
    protected int age;
    protected String sex;
    protected double price;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return age == animal.age
                && Double.compare(price, animal.price) == 0
                && Objects.equals(name, animal.name)
                && Objects.equals(sex, animal.sex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, sex, price);
    }

    public Animal(String name, int age, String sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    @Override
    public abstract String toString();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
