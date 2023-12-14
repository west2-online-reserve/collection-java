package work2;

/**
 * @author Mia
 * @date 2023/11/1
 */
public class Cats extends Animal {
    public Cats() {
    }

    public Cats(String name, int age, String sex) {
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        return "这只猫猫的姓名为：" + name + "   年龄为：" + age + "   性别为：" + sex + "   价格为：" + price;
    }
}
