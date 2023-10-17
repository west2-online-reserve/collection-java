/**
 * @author XiaoxiongMeng
 */
public class CuteCats extends BaseAnimal{
    private final static int PRICE = 200;

    public static int sum = 0;

    public CuteCats(String name, int age, int sex) {
        super(name, age, sex, PRICE);
        sum++;
    }

    @Override
    public String toString() {
        return "可爱的猫咪！\n" +
                "姓名：" + name +
                "\n年龄：" + age +
                "\n性别：" + (sex == 0 ? "雌性" : "雄性") +
                "\n价格：" + price;
    }
}
