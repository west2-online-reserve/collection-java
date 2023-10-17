/**
 * @author 12949
 */
public class Cat extends Animal {
    public Cat(String name, int age, boolean sex, double restorePrice) {
        super(name, age, sex, 200.0, restorePrice);
    }

    @Override
    public String toString() {
        return "Cat{name:" + getName() +
                ",age:" + getAge() +
                ",sex:" + (getSex() ? "boy" : "girl") +
                ",price:" + getPrice() +
                ",restorePrice:" + getRestorePrice() + "}";

    }
}


