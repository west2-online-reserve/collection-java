public class ChineseFieldDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseFieldDog(String name, int age, String sex, double price, boolean isVaccineInjected) {
        super(name, age, sex, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        this.isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseFieldDog{" +
                "isVaccineInjected=" + isVaccineInjected +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", price=" + price +
                '}';
    }
}
