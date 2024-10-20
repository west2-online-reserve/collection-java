package Work1;

public class ChineseFieldDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseFieldDog(String name, int age, String sex, boolean isVaccineInjected) {
        super(name, age, sex, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "品种:" + "中华田园犬"
                + "\n姓名:" + name
                + "\n年龄:" + age
                + "\n性别:" + sex
                + "\n是否注射狂犬病疫苗:" + isVaccineInjected
                + "\n价格:" + value;
    }


}
