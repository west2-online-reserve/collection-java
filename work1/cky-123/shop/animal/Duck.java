package shop.animal;

/**
 * Describe:
 *
 * @author cky
 * @date 2025/10/22
 */
public class Duck extends  Animal{

    public Duck(String name, int age, String sex, double price) {
        super(name, age, sex, price);
        this.setPrice( 66 );
    }

    @Override
    public void bark() {
        System.out.println("嘎嘎嘎");
    }

    @Override
    public String toString() {
        return "Duck{" +
                "name='" + super.getName() + '\'' +
                ", age=" + super.getAge() +
                ", sex='" + super.getSex() + '\'' +
                ", price=" + super.getPrice() +
                '}';
    }
}
