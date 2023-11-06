package Xuxiiii;

public class Dog extends Animal{
    private boolean isVaccineInjected;
    public Dog(String name, int age, String sex,double price,boolean isVaccineInjected) {
        super(name, age, sex, price);
        this.setPrice(100);
    }

    @Override
    public String toString() {
        return "A Dog" + '\n' +
                "name is" + name +
                "age is" + age+
                "sex is"+sex+
                "price is 100"+
                "isVaccineInjected"+isVaccineInjected;
    }
}
