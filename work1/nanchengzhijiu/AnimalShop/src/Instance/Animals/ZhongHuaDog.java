package Instance.Animals;

import Instance.Animal;

class ZhongHuaDog extends Animal {
    boolean isVaccineInjected;
    public static final double PRICE=100;
    public ZhongHuaDog(String name, int age, String gender, boolean isVaccineInjected) {
        super(name, age, gender,PRICE);
        this.isVaccineInjected=isVaccineInjected;
    }
    @Override
    public String toString() {
        return  "狗狗姓名：" + name + ',' +
                "年龄：" + age + "岁" +','+
                "性别：" + gender + ',' +
                "价格：" + price + "元" +','+
                "是否注射疫苗：" + (isVaccineInjected ? "已注射" : "未注射");
    }
}
