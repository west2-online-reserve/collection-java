package work2;

/**
 * @author Mia
 * @date 2023/11/1
 */
public class Rabbit extends Animal {
    public Rabbit() {
    }

    public Rabbit(String name, int age, String sex) {
        super(name, age, sex, 150);
    }

    @Override
    public String toString() {
        return "这只兔子的姓名为：" + name + "   年龄为：" + age + "   性别为：" + sex + "   价格为：" + price;
    }
}
