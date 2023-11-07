/**
 * 抽象动物类用于后续继承
 * 包含动物的基本信息
 *
 * @author pany0593
 * @date 2023/10/30
 */
public abstract class AbstractAnimal {
    protected String name;
    protected int age;
    protected String gender;
    protected double price;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    AbstractAnimal(String name, int age, String gender, double price) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.price = price;
    }

    /**
     * 按一定格式返回类的基本信息
     *
     * @return String
     */
    @Override
    public abstract String toString();
}
