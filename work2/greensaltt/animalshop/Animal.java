package animalshop;

/**
 * Animal类抽象所有宠物的共性
 *
 * 约束Animal类应有的方法
 */
public abstract class Animal {

    /**
     * 动物名称
     */
    public String animalName;

    /**
     * 动物年龄
     */
    public int age;

    /**
     * 动物性别
     */
    public char sex;

    /**
     * 动物进价
     */
    public double price;

    /**
     * 动物售价
     */
    public double sell;

    /**
     * 无参构造
     */
    public Animal() {}

    /**
     *
     *全参构造方法
     *
     * @param animalName 动物名称
     * @param age 动物年龄
     * @param sex 动物性别
     * @param price 动物售价
     */
    public Animal(String animalName, int age, char sex, double price, double sell) {
        this.animalName = animalName;
        this.age = age;
        this.sex = sex;
        this.price = price;
        this.sell = sell;
    }

    /**
     * 抽象的toString方法
     */
    public abstract String toString();

}
