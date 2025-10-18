package example;

public abstract class Animal {
    protected String name;
    protected int age;
    protected Gender sex;
    protected double price;
    public Animal(String name, int age, Gender sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getSex() {
        return sex;
    }

    public double getPrice() {
        return price;
    }


}
