/**
 * Rabbit类表示动物“兔子”
 *
 * @author AGoodYear
 * @date 2023/11/4
 */
public class Rabbit extends AbstractAnimal{
    public Rabbit(String name, int age, int gender) {
        super(name, age, gender, 250, "兔子");
    }

    @Override
    public double getProfit() {
        return 100;
    }

    @Override
    public String toString() {
        String temp = "动物信息：\n" + "类别：兔子\n姓名：" + name + "\n年龄：" + age + "\n性别：";
        if (gender == 1) {
            temp += "雄性\n";
        } else {
            temp += "雌性\n";
        }
        temp += "价格：" + (price+100);
        return temp;
    }
}
