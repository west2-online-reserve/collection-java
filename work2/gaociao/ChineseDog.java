public class ChineseDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseDog() {
        this.price = 100;
    }

    public ChineseDog(String name, int age, double price, String sex, boolean isVaccineInjected) {
        super(name, age, 100, sex);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "ChineseDog{" +
                "isVaccineInjected=" + isVaccineInjected +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", price=" + price +
                ", sex='" + sex + '\'' +
                '}';
    }
}
