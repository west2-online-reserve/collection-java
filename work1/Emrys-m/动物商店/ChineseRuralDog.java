package HomeWork;

public class ChineseRuralDog extends Animal {
    public boolean isVaccineInjected;

    public ChineseRuralDog (String name,int age,String gender,boolean isVaccineInjected) {
        super(name,age,gender,100,80);
        this.isVaccineInjected = isVaccineInjected;
    }

    public ChineseRuralDog (){};

    @Override
    public String toString() {
        String str1 = ("ChineseRuralDog's name:"+this.name);
        String str2 = ("age:"+this.age);
        String str3 = ("gender:"+this.gender);
        String str4 = ("price:"+this.price);
        if (this.isVaccineInjected)
            return str1 + "\n" + str2 + "\n" + str3 + "\n" + str4 + "\n" + "It had been injected vaccine";
        else return str1 + "\n" + str2 + "\n" + str3 + "\n" + str4 + "\n" + "It had not been injected vaccine";
    }
}
