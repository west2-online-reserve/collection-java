package Eyrine;

public class Dog extends Animal{
    private boolean  isVaccineInjected;


    public Dog(String name, int age, String gender, double price, boolean isVaccineInjected) {
        super(name, age, gender, price);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "中华田园犬 "+getName()+",价格：" +getPrice()+",是否注射疫苗："+isVaccineInjected;
    }
}
