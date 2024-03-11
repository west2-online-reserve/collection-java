public class ChineseRuralDog extends Animal {
    protected boolean vaccineInjected=true;

    public ChineseRuralDog(String animalName, int age, String gender, boolean vaccineInjected) {
        super(animalName, age, gender, 100.0);
        this.vaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "中华田园犬{" +
                "动物名='" + animalName + '\'' +
                ", 年龄=" + age +
                ", 性别='" + gender + '\'' +
                ", 是否注射狂犬病疫苗=" + vaccineInjected +
                ", 价格=" + price +
                '}';
    }

    public boolean isVaccineInjected() {
        return vaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        this.vaccineInjected = vaccineInjected;
    }
}
