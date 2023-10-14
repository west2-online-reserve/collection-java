import java.time.LocalDate;
import java.util.ArrayList;

public class Customer extends Animal {
    protected LocalDate latestComeShopTime;//最新到店时间
    protected int shopTimes;
    ArrayList<Animal> animals = new ArrayList<>();//顾客拥有的animal

    public void buyAnimal(Animal animal) {
        animals.add(animal);
    }

    public Customer(String name, int age, boolean sex) {
        super(name, age, sex, -1, -1);
        shopTimes = 0;
    }

    @Override
    public String toString() {
        return "Customer:"
                + name
                + "\nsex:"
                + ((sex) ? "boy" : "girl")
                + "\nshopTimes:"
                + shopTimes
                + "\nlatest go shopping time:"
                + latestComeShopTime.toString()
                + "\n拥有的动物:"
                + animals.toString();
    }

    public void increaseShopTimes() {
        ++shopTimes;
    }

}
