/**
 * @author DriOgon
 * @data 2023/11/4
 */
public abstract class Animal {
    protected String name;
    protected int age;
    protected String sex;
    protected double price;

    public Animal(String name, int age, String sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

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

    public abstract String toString();
}




