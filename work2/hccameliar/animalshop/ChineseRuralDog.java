package animalshop;

/**
 * @author 102301412
 */
public class ChineseRuralDog extends BaseAnimal {
    private boolean isVaccineInjected;
    private static final double price = 100;

    public ChineseRuralDog() {
    }

    public ChineseRuralDog(String petName, int age, char gender, boolean isVaccineInjected) {
        super(petName, age, gender, price);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "{中华田园犬：" + "姓名：" + getPetName() + " 年龄：" + getAge() + " 性别：" + getGender() + '}';
    }
}
