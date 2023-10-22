/**
 * Date 2023/10/18  13:34
 *
 * @author Kkkrran
 * @version 1.0
 */
public class Mouse extends Animal {
    protected double weight;

    public Mouse(String name, int age, String sex, double price, double weight) {
        super(name, age, sex, 20);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Mouse{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
