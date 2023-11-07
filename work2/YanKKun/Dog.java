package Animal;

/**
 *
 * @author 12080
 * 继承于抽象类Animals的Dog类
 *
 **/
public class Dog extends Animal{

    // 注射狂犬疫苗
    private boolean isVaccineInjected;

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    public Dog(String name, int age, String gender, double price, boolean isVaccineInjected) {
        super(name, age, gender, 100,"中华田园犬");
        this.isVaccineInjected = isVaccineInjected;
    }


    @Override
    public String toString() {
        return  "Dog{" +
                "Name='" + name + '\'' +
                ", Age=" + age +
                ", Gender='" + gender + '\'' +
                ", Price=" + price +
                ", Species='" + species + '\'' +
                '}';
    }
}
