package work2;

class ChineseDog extends Animals {
    boolean isVaccineInjected;

    public ChineseDog(String name, int age, String gender, double price, boolean isVaccineInjected) {
        super(name, age, gender, price);
        isVaccineInjected = isVaccineInjected;
        this.price = price;

    }

    @Override
    public String toString() {
        return "Chinese dog:" + name +
                ", gender:" + gender +
                ", age:" + age +
                ", price:" + price +
                ", isVaccineInjected:" + isVaccineInjected
                ;
    }
}
