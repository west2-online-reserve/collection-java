import java.util.Objects;

public abstract class Animal {
    protected String name;
    protected int age;
    // 性别，true为雄性，false为雌性
    protected boolean sex;
    protected double price;

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

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Animal(String name, int age, boolean sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;

    }

    public Animal() {
    }

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return sex == animal.sex && Objects.equals(name, animal.name);
    }
}