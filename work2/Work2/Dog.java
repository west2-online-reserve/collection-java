package Work2;

public class Dog extends Animal{
    private boolean isVaccineInjected;
    public Dog(String name, int age, String gender, double price){

        super(name, age, gender,100);
        this.isVaccineInjected=isVaccineInjected;
    }

    public Dog(String aatrox, String number, String male, String number1) {
    }

    @Override
    public String toString() {
        return "名字"+name+",年龄"+age+",性别"+gender+",价格"+price;
    }
}
