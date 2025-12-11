package shop.animal;

/**
 * Describe:
 *
 * @author cky
 * @date 2025/10/22
 */
public class Cat  extends Animal{

    public Cat(String name, int age, String sex, double price) {
        super(name, age, sex, price);
        this.setPrice(200);
    }

    @Override
    public void bark() {
        System.out.println("喵喵喵");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", sex='" + getSex() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
