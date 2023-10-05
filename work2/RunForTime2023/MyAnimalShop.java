import java.time.LocalDate;
import java.util.ArrayList;

class MyAnimalShop implements AnimalShop {
    private double balance;
    private boolean isClosed;
    private double profit;
    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();
    private LocalDate openDate;

    MyAnimalShop(double balance, LocalDate openDate) {
        this.balance = balance;
        this.isClosed = false;
        this.openDate = openDate;
    }

    public void openShop(LocalDate openDate) {
        isClosed = false;
        this.openDate = openDate;
    }

    public void buyAnimals(Animal animal) {
        if (balance < animal.price) {
            throw new InsufficientBalanceException();
        } else {
            animals.add(animal);
            balance -= animal.price;
            profit -= animal.price;
        }
    }

    public void receiveCustomers(Customer customer, Animal animal) {
        boolean come = false;
        for (Customer x : customers) { //是否是老顾客
            if (x.getName().equals(customer.getName())) {
                come = true;
                x = customer;
                break;
            }
        }
        if (!come) {
            customers.add(customer);
        }

        boolean isHere = false;
        for (Animal x : animals) { //查找是否有顾客指定的宠物
            if (x.name.equals(animal.name)) {
                balance += animal.price;
                profit += animal.price;
                System.out.println(animal);
                animals.remove(animal);
                isHere = true;
                break;
            }
        }
        if (!isHere) {
            throw new AnimalNotFoundException();
        }
    }

    public void closeShop() {
        //我不知道所谓的"LocalDate判断"是什么意思，所以这里并没有判断打烊时间
        isClosed = true;
        System.out.printf("Today's customers List(%s):\n", String.valueOf(openDate));

        for (Customer x : customers) {
            System.out.print(x.toString());
        }
        customers.clear();
        System.out.printf("Today's profit: %.2f 元\n\n", profit);
        profit = 0;
    }
}