public abstract class Animal{
    protected String name;
    protected int age;
    protected boolean sex;//true:female false:man
    protected double price;
    protected static final int ANIMALKIND=3;

    public Animal() {
        this.name = "Animal";
        this.age = 10;
        this.sex = true;
        this.price = 0;
    }

    public Animal(String name, int age, boolean sex, double price) {
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

    public boolean isSex() {
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
    public abstract String toString();
}

