package animal;

public class CRDog extends Animal {
    private boolean isVaccineInjected = false;
    //重写父类方法
    public CRDog(String name, int age, String gender,boolean isVaccineInjected) {
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public String toString(){
        return "狗名字:" + getName() + ",年龄:" + getAge() + ",价格:" + getPrice();
    }

}
