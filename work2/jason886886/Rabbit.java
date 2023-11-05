package work2;

/**
 * @author jason
 */
public class Rabbit extends AbstractAnimal {
    public Rabbit(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }

    @Override
    public String toString() {
        return "名字:" + this.name + "\n年龄:" + this.age + "\n性别:" + this.gender + "/n价格:" + this.price + "\n";
    }
}
