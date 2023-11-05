/**
 * 动物抽象类，包含动物共有的信息
 *
 * @author AGoodYear
 * @date 2023/11/4
 */
public abstract class AbstractAnimal {
    protected String name;
    protected int age;
    /**
     * 1表示雄性，2表示雌性
     */
    protected int gender;
    protected double price;
    protected String type;
    public AbstractAnimal(String name, int age, int gender, double price, String type) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getGender() {
        return gender;
    }
    public double getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 输出对象的所有信息
     *
     * @return 该对象的信息
     */
    @Override
    public abstract String toString();

    /**
     * 获得当前动物利润
     *
     * @return 当前动物利润
     */
    public abstract double getProfit();
}
