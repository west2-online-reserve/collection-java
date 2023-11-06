/**
 * Dog类表示动物“中华田园犬”
 *
 * @author AGoodYear
 * @date 2023/11/4
 */
public class Dog extends AbstractAnimal{
    public Dog(String name, int age, int gender, boolean isVaccineInjected) {
        super(name, age, gender, 100, "狗");
        this.isVaccineInjected = isVaccineInjected;
    }
    private boolean isVaccineInjected;

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public boolean getIsVaccineInjected() {
        return isVaccineInjected;
    }

    @Override
    public double getProfit() {
        return 50;
    }

    @Override
    public String toString() {
        String temp = "动物信息：\n" + "类别：中华田园犬\n姓名：" + name + "\n年龄：" + age + "\n性别：";
        if (gender == 1) {
            temp += "雄性\n";
        } else {
            temp += "雌性\n";
        }
        temp += "价格：" + (price+50) + "\n是否注射疫苗:";
        if (isVaccineInjected) {
            temp += "是";
        } else {
            temp += "否";
        }
        return temp;
    }
}
