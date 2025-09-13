/**
 * @author pengyuezhe
 */
public class Cat extends Animal {
    private String name;
    private int age;
    private String sex;
    public Cat(String name, int age, String sex) {
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        return ("动物名" + name +
                "\n年龄" + age +
                "\n性别" + sex +
                "\n价格" + price);
    }

}
