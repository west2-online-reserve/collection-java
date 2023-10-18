public abstract class Animal {
    protected String name;
    protected int age;
    protected boolean sex;
    protected double price;

    public Animal(String name, int age, boolean sex, double price) {
        this.setName(name);
        this.setAge(age);
        this.setSex(sex);
        this.setPrice(price);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public abstract String toString();
}
