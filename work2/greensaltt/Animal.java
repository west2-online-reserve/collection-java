package animalshop;

/**
 * Animal类抽象所有宠物的共性
 * <p>
 * 约束Animal类应有的方法
 */
public abstract class Animal {

    /**
     * 动物名称
     */
    private String animalName;

    /**
     * 动物年龄
     */
    private int age;

    /**
     * 动物性别
     */
    private char sex;

    /**
     * 动物进价
     */
    private double price;

    /**
     * 动物售价
     */
    private double sell;

    /**
     * 无参构造
     */
    public Animal() {
    }

    /**
     * 全参构造方法
     *
     * @param animalName 动物名称
     * @param age        动物年龄
     * @param sex        动物性别
     * @param price      动物售价
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

    /**
     * get取得动物信息
     *
     * @return
     */
    public String getAnimalName() {
        return animalName;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public double getPrice() {
        return price;
    }

    public double getSell() {
        return sell;
    }

}
