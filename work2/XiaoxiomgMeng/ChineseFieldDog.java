/**
 * @author XiaoxiongMeng
 */
public class ChineseFieldDog extends BaseAnimal{
    private final static int PRICE = 100;

    private boolean isVaccineInjected;
    //是否注射疫苗

    public static int sum = 0;
    public ChineseFieldDog(String name, int age, int sex) {
        super(name, age, sex, PRICE);
        sum++;
    }

    @Override
    public String toString() {
        return "中华田园犬\n" +
                "姓名：" + name +
                "\n年龄：" + age +
                "\n性别：" + (sex == 0 ? "雌性" : "雄性") +
                "\n价格：" + price +
                "\n是否注射疫苗：" + isVaccineInjected;
    }
}
