package work2;

public class Dog extends Animal {
    private boolean isVaccineInjected;

    public Dog() {
        super.setPrice(100);
    }

    public Dog(String name, int age, String gender, double price,boolean isVaccineInjected) {
        super(name, age, gender, price);
        super.setPrice(100);
        this.isVaccineInjected = isVaccineInjected;
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "狗狗{"  + "价格：" + getPrice() + ", 名字：" + getName() + ", 年龄：" + getAge() + ", 性别：" + getGender() + ", 是否打了疫苗：" + isVaccineInjected+ '}';
    }
}
