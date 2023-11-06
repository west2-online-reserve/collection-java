package work2;

public class Dog extends Animal {
    private boolean isVaccineInjected;

    public Dog() {
        super.price = 100;
    }

    public Dog(String name, int age, String gender, double price,boolean isVaccineInjected) {
        super(name, age, gender, price);
        super.price = 100;
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "狗狗{"  + "价格：" + price + ", 名字：" + name + ", 年龄：" + age + ", 性别：" + gender + ", 是否打了疫苗：" + isVaccineInjected+ '}';
    }
}
