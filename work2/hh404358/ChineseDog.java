class ChineseDog extends Animal{
    public boolean isVaccineInjected;

    public ChineseDog() {
        super();
        this.name="ChineseDog";
        this.price=100;
    }

    public ChineseDog(String name, int age, boolean sex, double price ) {
        super(name, age, sex, price);
        isVaccineInjected=true;
    }

    @Override
    public String toString() {
        return "ChineseDog{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", price=" + price +
                "isVaccineInjected=" + isVaccineInjected +
                '}';
    }

}