/*兔类*/
public class Rabbit extends Animal {
    protected boolean isVaccineInjected;

    public Rabbit(String name, int age, String gender, double price, boolean isVaccineInjected) {
        super(name, age, gender, 30);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "它的品种是：兔子\n" +
                "它的年龄是: " + this.age + "\n" +
                "它的名字是: " + this.name + "\n" +
                "它是否打过疫苗: " + this.isVaccineInjected + "\n" +
                "它的价格是: " + this.price + "元" + "\n";
    }
}
