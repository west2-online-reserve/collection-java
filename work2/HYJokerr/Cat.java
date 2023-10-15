public class Cat extends Animal {
    private boolean isVaccineInjected;

    public Cat(String name, int age, String sex, boolean isVaccineInjected) {
        super(name, age, sex, 200);
        this.isVaccineInjected = isVaccineInjected;
    }


    @Override
    public String toString() {

        return "种类：猫\n"+"名字："+name+"\n年龄:"+age+"\n性别:"+sex+"\n价格:"+price+"\n是否注射狂犬疫苗:"+isVaccineInjected;
    }
}
