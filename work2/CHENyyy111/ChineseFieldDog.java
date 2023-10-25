package AnimalShop;

public class ChineseFieldDog extends Animal {

    protected boolean isVaccineInjected;

    public ChineseFieldDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public void setIsVaccineInjected(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean getIsVaccineInjected() {
        return isVaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseFieldDog{" + "name=" + name + ", age=" + age + ", gender=" + gender + ", price=" + price + ", isVaccineInjected=" + isVaccineInjected + "}";
    }
}
