package work2;
/**
 * @author Mia
 * @date 2023/11/1
 */
public abstract class Animal {
    protected String name;
    protected int age;
    protected String sex;
    protected double price;

    public Animal() {
    }

    public Animal(String name, int age, String sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    /**
     * 输出动物信息
     *
     * @return null
     */
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
