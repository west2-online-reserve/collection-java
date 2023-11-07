package HomeWork;

public class ChineseRuralDog extends Animal{
    public ChineseRuralDog() {
    }

    public ChineseRuralDog(String name, int age, char gender) {

        super(name, age, gender, 100);
    }
    private boolean isVaccineInjected;

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {

        return "中华田园犬的名字是"+
                getName()+", "+"年龄是"+getAge()+", "+"性别是"+getGender()+", "+"价格是"+getPrice();
    }
}
