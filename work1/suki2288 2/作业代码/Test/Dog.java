package Test;

public class Dog extends Animal{

    protected boolean isVaccineInjected;

    public Dog(String name, int age, String gender, boolean isVaccineInjected){
        // 直接把狗狗价格固定为200元
        super(name, age, gender, 100);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "isVaccineInjected=" + isVaccineInjected +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", price=" + price +
                '}';
    }
}

