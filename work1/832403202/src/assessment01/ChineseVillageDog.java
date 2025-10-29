package assessment01;

public class ChineseVillageDog extends Animal{
    private boolean isVaccineInjected; // 是否注射狂犬病疫苗

    // 构造方法
    public ChineseVillageDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender, 100); // 价格固定为100元
        this.isVaccineInjected = isVaccineInjected;
    }

    // 重写toString方法
    @Override
    public String toString() {
        return "中华田园犬 [名字=" + getName() + ", 年龄=" + getAge() + ", 性别=" + getGender() +
                ", 价格=" + getPrice() + ", 是否注射狂犬病疫苗=" + isVaccineInjected + "]";
    }
}
