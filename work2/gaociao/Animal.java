public abstract class Animal {
    protected String name;
    protected int age;
    protected double price;
    protected String sex;

    public Animal() {
    }

    public Animal(String name, int age, double price, String sex) {
        this.name = name;
        this.age = age;
        this.price = price;
        this.sex = sex;
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


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }


    public String getSex() {
        return sex;
    }


    public void setSex(String sex) {
        this.sex = sex;
    }

    public abstract String toString();
}
