package shop;

// ChineseRuralDog.java
public class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100.0);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "中华田园犬{" +
                "名字='" + name + '\'' +
                ", 年龄=" + age +
                ", 性别='" + gender + '\'' +
                ", 是否注射疫苗=" + isVaccineInjected +
                ", 价格=" + price*2 +
                '}';
    }
}
