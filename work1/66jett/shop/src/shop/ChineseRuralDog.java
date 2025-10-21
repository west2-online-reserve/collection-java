package shop;

/**
 * 中华田园犬类
 */
public class ChineseRuralDog extends Animal {

    /**
     * 是否注射狂犬病疫苗
     */
    private boolean isVaccineInjected;

    /**
     * 构造方法
     */
    public ChineseRuralDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100.0);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return String.format("中华田园犬 [名字: %s, 年龄: %d岁, 性别: %s, 价格: %.2f元, 是否注射疫苗: %s]",
                getName(), getAge(), getGender(), getPrice(),
                isVaccineInjected ? "是" : "否");
    }
}