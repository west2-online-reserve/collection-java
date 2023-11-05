package HomeWork;

public class ChineseRuralDog extends Animal{
    public ChineseRuralDog() {
    }

    public ChineseRuralDog(String name, int age, char gender) {

        super(name, age, gender, 100);
    }
    public boolean isVaccineInjected;

    @Override
    public String toString() {

        return "中华田园犬的名字是"+name+", "+"年龄是"+age+", "+"性别是"+gender+", "+"价格是"+price;
    }


}
