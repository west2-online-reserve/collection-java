package java_work2;

/**
 * ChineseRuralDog类
 * 继承于抽象Animal类
 *
 * @author formrc
 * @date 2023/10/23
 */
class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;
    private static final double defaultPrice = 100.0;
    public ChineseRuralDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, defaultPrice);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean getIsVaccineInjected() {
        return isVaccineInjected;
    }

    public void setIsVaccineInjected(boolean isVaccineInjected) {
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return  "Chinese Rural Dog: " + "\n" +
                "name: " + name + "\n" +
                "Age: " + age + "\n" +
                "Gender: " + gender + "\n" +
                "Vaccine Injected: " + isVaccineInjected + "\n" +
                "Price: " + price + "\n" ;
    }
}
