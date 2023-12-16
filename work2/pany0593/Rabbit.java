/**
 * 兔子类
 * 增加颜色属性
 * 继承于抽象动物类
 *
 * @author pany0593
 * @date 2023/10/30
 */
public class Rabbit extends AbstractAnimal {
    private String color;

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return this.color;
    }

    Rabbit(String name, int age, String gender, String color) {
        super(name, age, gender, 150);
        this.setColor(color);
    }

    @Override
    public String toString() {
        return "姓名：" + this.getName() + "\n" +
                "年龄：" + this.getAge() + "\n" +
                "性别：" + this.getGender() + "\n" +
                "价格：" + this.getPrice() + "\n" +
                "颜色：" + this.getColor() + "\n";
    }
}
