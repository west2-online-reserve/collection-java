import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private boolean isOpen;
    private List<Animal> animals;
    private List<Customer> customers;

    public MyAnimalShop(double initialBalance) {
        this.balance = initialBalance;
        this.isOpen = true;
        this.animals = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    public void buyAnimal(int animalType, String name, int age, String gender, boolean hasRabiesVaccine) throws InsufficientBalanceException {
        Animal animal;
        double animalCost;

        switch (animalType) {
            case 1:
                animal = new Cat(name, age, gender);
                animalCost = 200.0;
                break;
            case 2:
                animal = new ChineseFarmDog(name, age, gender, hasRabiesVaccine);
                animalCost = hasRabiesVaccine ? 200.0 : 100.0; 
                break;
            case 3:
                animal = new Monkey(name, age, gender);
                animalCost = 100.0;
                break;
            default:
                throw new IllegalArgumentException("无效的动物类型");
        }

        if (balance < animalCost) {
            throw new InsufficientBalanceException("余额不足，无法购买动物。");
        }

        animals.add(animal);
        balance -= animalCost;
        System.out.println("买入动物: " + animal);
        System.out.println("当前余额: " + balance);
    }

    public void attendCustomer(Customer customer) throws AnimalNotFoundException {
        System.out.println("招待顾客: " + customer);
        customers.add(customer);
        customer.incrementVisitCount();
        customer.setLastVisitDate(LocalDate.now());
    }

    public void closeShop() {
        isOpen = false;
        System.out.println("商店已关闭");
    }

    public void openShop() {
        isOpen = true;
        System.out.println("商店已开启");
    }
}
