package shop.animal;

/**
 * Describe:
 *
 * @author cky
 * @date 2025/10/22
 */
public abstract class Animal {

    // 属性 名字
    private String name;
    // 属性 年龄
    private int age;
    // 属性 性别
    private String sex;
    // 属性 价格
    private double price;

    /**
     * 构造方法
     *
     * @param name
     * @param age
     * @param sex
     * @param price
     */
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

    /**
     * 抽象方法 抽象方法
     */
    public abstract void bark();

    /**
     * 抽象方法 抽象方法
     */
    @Override
    public abstract String toString();
}
