package work2;

class ChineseDog extends Animals {
    private boolean isVaccineInjected;

    public ChineseDog(String name, int age, String gender, double price, boolean isVaccineInjected) {
        super(name, age, gender, price);
        isVaccineInjected = isVaccineInjected;
        this.setPrice(price);

    }

    @Override
    public String toString() {
        return "Chinese dog:" + getName() +
                ", gender:" + getGender() +
                ", age:" + getAge() +
                ", price:" + getPrice() +
                ", isVaccineInjected:" + isVaccineInjected
                ;
    }
}
