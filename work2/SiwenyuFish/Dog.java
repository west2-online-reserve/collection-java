package work2;

public class Dog extends Animal{

    boolean isVaccineInjected;

    public Dog(String name, int age, String sex, boolean isVaccineInjected) {
        super(name, age, sex, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "isVaccineInjected=" + isVaccineInjected +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", price=" + price +
                '}';
    }
}
