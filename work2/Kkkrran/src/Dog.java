/**
 * Date 2023/10/18  13:34
 *
 * @author Kkkrran
 * @version 1.0
 */
public class Dog extends Animal {
    protected boolean isVaccineInjected;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", price=" + price +
                ", isVaccineInjected=" + isVaccineInjected +
                '}';
    }

    public Dog(String name, int age, String sex, double price, boolean isVaccineInjected) {
        //fix_bug: price constantly equals 100
        super(name, age, sex, price);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }
}
