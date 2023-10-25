public class Dog extends Animal {
    private boolean vaccineInjected;
    private double price;

    public Dog() {
    }

    public Dog(String name, int age, boolean sex, boolean vaccineInjected) {
        super(name, age, sex, 100);
        this.vaccineInjected = vaccineInjected;
    }

    public boolean getVaccineInjected() {
        return vaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        this.vaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", age=" + age +
                ", " + (sex ? "雄" : "雌") +
                ", " + (vaccineInjected ? "已接种狂犬病疫苗" : "未接种狂犬病疫苗") +
                '}';
    }

}