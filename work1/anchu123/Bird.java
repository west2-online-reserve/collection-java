/*鸟类*/
public class Bird extends Animal {
    protected boolean isVaccineInjected;

    public Bird(String name, int age, String gender, double price, boolean isVaccineInjected) {
        super(name, age, gender, 50);
        this.isVaccineInjected = isVaccineInjected;
    }

    @Override
    public String toString() {
        return "它的品种是：鸟\n" +
                "它的年龄是: " + this.age + "\n" +
                "它的名字是: " + this.name + "\n" +
                "它是否打过疫苗: " + this.isVaccineInjected + "\n" +
                "它的价格是: " + this.price + "元" + "\n";
    }
}
