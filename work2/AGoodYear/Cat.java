/**
 * Cat类代表动物“猫”
 *
 * @author AGoodYear
 * @date 2023/11/4
 */
public class Cat extends AbstractAnimal{
    public Cat(String name, int age, int gender) {
        super(name, age, gender, 200, "猫");
    }
    @Override
    public double getProfit()
    {
        return 70;
    }
    @Override
    public String toString() {
        String temp = "动物信息：\n" + "类别：猫\n姓名：" + name + "\n年龄：" + age + "\n性别：";
        if (gender == 1) {
            temp += "雄性\n";
        } else {
            temp += "雌性\n";
        }
        temp += "价格：" + (price+70);
        return temp;
    }
}
