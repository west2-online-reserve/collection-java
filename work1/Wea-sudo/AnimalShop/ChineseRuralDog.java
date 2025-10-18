package example;

public class ChineseRuralDog extends Animal {
    private boolean isVaccineInjected;

    public ChineseRuralDog(String name, int age, Gender sex, boolean isVaccineInjected) {
        super(name,age,sex,100, "中华田园犬");
        this.isVaccineInjected = isVaccineInjected;

    }

    @Override
    public String toString() {
        String isVaccineString = "";
        if (isVaccineInjected) {
            isVaccineString = "是";
        } else {
            isVaccineString = "否";
        }
        return "姓名" + name +
                ", 类型:" + className +
                ", 性别:" + sex +
                ", 年龄:" + age +
                ", 是否注册疫苗:" + isVaccineString;
    }


}
