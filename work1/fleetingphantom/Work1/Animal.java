package Work1;

public abstract class Animal {
    protected String name;
    protected int age;
    protected String sex;
    protected double value;

    public Animal(String name, int age, String sex, double value) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.value = value;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public abstract String toString();

}
