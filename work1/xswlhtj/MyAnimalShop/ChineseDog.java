public class ChineseDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseDog(String animalName, int age, String sex, double price, boolean isVaccineInjected) {
        super(animalName, age, sex, 100.0);
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
                "animalName='" + animalName + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", price=" + price +
                ", isVaccineInjected=" + isVaccineInjected +
                '}';
    }
}
