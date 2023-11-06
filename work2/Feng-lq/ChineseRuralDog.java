package work2;

/**
 * @author FAN
 */
public class ChineseRuralDog extends AbstractAnimal {
    private Boolean isVaccineInjected = false;

    public ChineseRuralDog(String name, int age, int gender) {
        super(name, age, gender, 100);
    }

    @Override
    public String toString() {
        return "姓名:" + name + "\t年龄:" + age + "\t性别(0/1):" + gender + "\t价格:" + price;
    }

    public Boolean getVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(Boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }
}
