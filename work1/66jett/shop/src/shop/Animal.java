package shop;

import java.util.Objects;

/**
 * 动物抽象类
 */
public abstract class Animal {

    /**
     * 动物名
     */
    private String name;

    /**
     * 年龄
     */
    private int age;

    /**
     * 性别
     */
    private String gender;

    /**
     * 价格
     */
    private double price;

    /**
     * 全参构造方法
     */
    public Animal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    // Getter和Setter方法
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

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
                && Double.compare(animal.price, price) == 0
                && Objects.equals(name, animal.name)
                && Objects.equals(gender, animal.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, gender, price);
    }

    /**
     * 抽象的toString方法
     */
    @Override
    public abstract String toString();
}