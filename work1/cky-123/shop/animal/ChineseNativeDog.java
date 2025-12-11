package shop.animal;

/**
 * Describe:
 *
 * @author cky
 * @date 2025/10/22
 */
public class ChineseNativeDog  extends  Animal{
    private boolean isVaccineInjected=  false;

    public ChineseNativeDog(String name, int age, String sex, double price, boolean isVaccineInjected) {
        super(name, age, sex, price);
        this.isVaccineInjected = isVaccineInjected;
        this.setPrice(100);
    }
    @Override
    public void bark() {
        System.out.println("汪汪汪");
    }

    @Override
    public String toString() {
        return "ChineseNativeDog{" +
                "name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", sex='" + super.getSex() + '\'' +
                ", price=" + super.getPrice() +
                ", isVaccineInjected=" + isVaccineInjected +
                '}';
    }

}
