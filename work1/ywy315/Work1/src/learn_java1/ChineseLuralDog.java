package learn_java1;

public class ChineseLuralDog extends Animal {
    private boolean isVaccineInjected;//是否打过疫苗

    public ChineseLuralDog(String name, int age, char gender, boolean isVaccineInjected, double price, double purchasePrice) {
        super(name, age, gender, price, purchasePrice);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return age + "岁的" + gender + "中华田园犬叫做" + name + (isVaccineInjected ? "，已" : "，未") + "打过疫苗" + "，价格为" + price;
    }

}
