/**
 * @author pengyuezhe
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

    /**
     * 抽象方法
     * @return 返回动物信息
     */
    @Override
    public abstract String toString( );
}
