public class ChinaDog extends Animal {
    protected boolean isVaccineInjected;//是否注射过疫苗


    ChinaDog(String name, int age, boolean sex, boolean isVaccineInjected, double restorePrice) {
        super(name, age, sex, 100.0, restorePrice);


        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "Name:" + name
                + "\nAge:" + age
                + "\nsex:" + (sex ? "boy" : "girl")
                + "\nPrice:" + price +
                "\nisVaccineInjected" + isVaccineInjected;
    }
}
