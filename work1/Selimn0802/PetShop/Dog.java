package PetShop;
//狗狗类
public class Dog extends Animal{
    private boolean isVaccineInjected;
    public Dog(String name, String sex, int age,boolean isVaccineInjected) {
        super("狗狗",name, sex, age, 50, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString () {
        return "狗狗 [姓名：" + name + ", 性别：" + sex + ", 年龄：" + age + ", " +
                "价格：" + sellPrice + ", 是否接种疫苗" + isVaccineInjected + "]";
    }


}
