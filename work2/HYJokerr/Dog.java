public class Dog extends Animal {
    //是否注射狂犬病疫苗
    private boolean isVaccineInjected;

    public Dog(String name, int age, String sex, boolean isVaccineInjected) {
        super(name, age, sex, 100);
        this.isVaccineInjected = isVaccineInjected;

    }

    @Override
    public String toString() {
        return "种类：狗\n"+"名字："+name+"\n年龄:"+age+"\n性别:"+sex+"\n价格:"+price+"\n是否注射狂犬疫苗"+isVaccineInjected;
    }
}
