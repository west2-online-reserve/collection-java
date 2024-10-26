import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class MyAnimalShop implements AnimalShop {
    private double balance;

    private List<ChineseDog> dogs;
    private List<Cat> cats;
    private List<Customer> customers;
    private boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.cats = new ArrayList<>();
        this.dogs = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal, int quantity) throws InsufficientBalanceException {
        double totalCost = animal.getPrice() * quantity;
        if (balance < totalCost) {
            throw new InsufficientBalanceException("余额不足，无法购买新动物。");
        }
        for (int i = 0; i < quantity; i++) {
            if (animal instanceof ChineseDog) {
                dogs.add((ChineseDog) animal);
            } else if (animal instanceof Cat) {
                cats.add((Cat) animal);
            }
        }
        balance -= totalCost;
        System.out.println("购买了新动物: " + animal + " (数量: " + quantity + ")");
    }

    @Override
    public void treatCustomer(Customer customer, String animalType, int quantity) throws AnimalNotFoundException {
        List<Animal> purchasedAnimals = new ArrayList<>();
        if (animalType.equalsIgnoreCase("ChineseDog")) {
            if (dogs.size() < quantity) {
                throw new AnimalNotFoundException("店内没有足够的狗，无法招待客户。");
            }
            for (int i = 0; i < quantity; i++) {
                purchasedAnimals.add(dogs.remove(0));
            }
        } else if (animalType.equalsIgnoreCase("Cat")) {
            if (cats.size() < quantity) {
                throw new AnimalNotFoundException("店内没有足够的猫，无法招待客户。");
            }
            for (int i = 0; i < quantity; i++) {
                purchasedAnimals.add(cats.remove(0));
            }
        } else {
            throw new AnimalNotFoundException("店内没有指定类型的动物，无法招待客户。");
        }
        customers.add(new Customer(customer.getEnterTimes() + 1, customer.getName(), LocalDate.now()));
        double totalCost = purchasedAnimals.stream().mapToDouble(Animal::getPrice).sum();
        balance += totalCost;
        customer.setEnterTimes(customer.getEnterTimes() + 1);

        System.out.println("客户 " + customer + " 购买了动物: " + purchasedAnimals);
    }

    @Override
    public void shopClose() {
        System.out.println("今天的顾客列表: " + customers);
        System.out.println("今日利润: " + balance);
        isOpen = false;
    }
}
