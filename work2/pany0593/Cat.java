/**
 * 猫猫类
 * 继承于抽象动物类
 *
 * @author pany0593
 * @date 2023/10/30
 */
public class Cat extends AbstractAnimal {
    Cat(String name, int age, String gender) {
        super(name, age, gender, 200);
    }

    @Override
    public String toString() {
        return "姓名：" + this.getName() + "\n" +
                "年龄：" + this.getAge() + "\n" +
                "性别：" + this.getGender() + "\n" +
                "价格：" + this.getPrice() + "\n";
    }
}
