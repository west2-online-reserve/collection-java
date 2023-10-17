/**
 * @author pengyuezhe
 */
public class ChineseRuralDog extends Animal {
    protected boolean isVaccineinjected;

    public ChineseRuralDog(String name, int age, String sex, boolean isVaccineinjected) {
        super(name, age, sex, 100);
        this.isVaccineinjected = isVaccineinjected;
    }

    @Override
    public String toString() {
        return ("动物名" + name +
                "\n年龄" + age +
                "\n性别" + sex +
                "\n价格" + price +
                "\n是否打狂犬疫苗" + isVaccineinjected);
    }
}
