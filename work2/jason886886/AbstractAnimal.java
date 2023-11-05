package work2;

/**
 * @author jason
 */
public abstract class AbstractAnimal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price;

    public AbstractAnimal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    /**
     * @return 返回对象所有信息.
     */
    @Override
    public abstract String toString();
}
