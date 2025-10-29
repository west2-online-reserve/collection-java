package Pet.shop;

public class ChineseRuralDog extends Animal{
    private boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, String gender,  boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseRuralDog{" +
                "isVaccineInjected=" + isVaccineInjected +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}
