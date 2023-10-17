import java.util.Objects;

/**
 * @author 12949
 */
public abstract class Animal {
    protected String name;
    protected int age;
    protected boolean sex;
    protected double price;
    protected double restorePrice;

    @Override
    public abstract String toString();

    public Animal(String name, int age, boolean sex, double price, double restorePrice) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
        this.restorePrice = restorePrice;
    }

    public double getRestorePrice() {
        return restorePrice;
    }

    public void setRestorePrice(double restorePrice) {
        this.restorePrice = restorePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public boolean getSex() {
        return sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Animal animal = (Animal) obj;
        return age == animal.age && Objects.equals(name, animal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}

