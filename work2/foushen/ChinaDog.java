public class ChinaDog extends Animal {
    private boolean isVaccineInjected;

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }


    public ChinaDog(String name, int age, boolean sex, boolean isVaccineInjected, double restorePrice) {
        super(name, age, sex, 100, restorePrice);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "ChinaDog{ name" + getName()
                + ", age:" + getAge()
                + ", sex:" + (getSex() ? "boy" : "girl") +
                ",isVaccineInjected:" + isVaccineInjected() +
                ", restorePrice:" + getRestorePrice() +
                '}';
    }
}
