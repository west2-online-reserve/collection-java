package org.example;

public class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseRuralDog(String n, int a, int s, boolean isVaccineInjected) {
        super(n, a, s, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public String toString() {
        String message = ("这只中华田园犬是" + (sex == 1 ? "雄性" : "雌性") + "中华田园犬，它的名字是" + name
                + "，它的年龄是" + age + "岁,它的价格是" + price + "元，" + (isVaccineInjected == true ? "已" : "未") + "注射狂犬病疫苗\n");
        return message;
    }

}
