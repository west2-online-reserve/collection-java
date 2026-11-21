package shop;

public class Dog extends Animal{
    private boolean isVaccineInjected;

    public Dog(String name, int age, String gender,boolean isVaccineInjected) {
        super(name, age, gender, 100.0);
        this.isVaccineInjected = isVaccineInjected;
    }
    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }
    String vaccineStr = isVaccineInjected ? "已打疫苗" : "未打疫苗";

    @Override
    public String toString() {
        return "狗！信息名字: " + getName() +
                "\n 年龄: " + getAge() + "岁" +
                ",\n性别: " + getGender() +
                ",\n价格: " + getPrice() + "元"+
                ",\n疫苗状态: "+ vaccineStr;
    }
}
