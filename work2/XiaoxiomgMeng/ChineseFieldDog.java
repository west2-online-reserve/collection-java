/**
 * @author XiaoxiongMeng
 */
public class ChineseFieldDog extends BaseAnimal{
    private final static int PRICE = 100;

    private boolean isVaccineInjected;

    public ChineseFieldDog(String name, int age, int sex,boolean isVaccineInjected) {
        super(name, age, sex, PRICE);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "中华田园犬\n"+ super.toString()+"\n是否注射疫苗："+isVaccineInjected;
    }
}
