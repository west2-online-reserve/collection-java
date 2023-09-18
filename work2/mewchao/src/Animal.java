/**
 * @projectName（项目名称）: west2online_work2
 * @className（类名称）: Animal
 * @description（类描述）: 宠物抽象类
 * @author（创建人）: mewchao
 * @createDate（创建时间）: 2023-09-18
 */
abstract class Animal {
    protected String name;
    protected int age;
    protected double price;
    protected boolean gender;

    public Animal(String name, int age, double price, boolean gender) {
        this.name = name;
        this.age = age;
        this.price = price;
        this.gender = gender;
    }

    public Animal() {
    }

    abstract public String toString();

    abstract public double getPrice();
}
