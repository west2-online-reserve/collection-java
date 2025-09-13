package work2;

/**
 * @author jason
 */
public class Dog extends AbstractAnimal {
    private boolean isVaccineInjected;

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    public Dog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "名字:" + this.name + "\n年龄:" + this.age + "\n性别:" + this.gender + "/n价格:" + this.price + "\n" + "是否注射狂犬病疫苗:" + isVaccineInjected + "\n";
    }
}
