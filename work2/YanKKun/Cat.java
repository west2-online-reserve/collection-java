package Animal;

/**
 *
 * @author 12080
 * 继承于抽象类Animals的cat类
 *
 **/
public class Cat extends Animal{

    public Cat(String name, int age, String gender, double price, String species) {
        super(name, age, gender, 200,species);
    }

    @Override
    public String toString() {
        return  "Cat{" +
                "Name='" + name + '\'' +
                ", Age=" + age +
                ", Gender='" + gender + '\'' +
                ", Price=" + price +
                ", Species='" + species + '\'' +
                '}';
    }
}
