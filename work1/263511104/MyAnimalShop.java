package work1;
import java.util.ArrayList;
import java.util.List;


public class MyAnimalShop implements AnimalShop{
    private double balance;
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (animal.getPrice() > balance) {
            throw new InsufficientBalanceException("余额不足，无法购买动物");
        }
        animalList.add(animal);
        balance -= animal.getPrice();
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFoundException {
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException("店内没有动物可售");
        }
        customerList.add(customer);
        Animal animal = animalList.remove(0);
        balance += animal.getPrice();
        System.out.println("出售动物：" + animal);
    }

    @Override
    public void closeShop() {
        if (!isOpen) return;
        System.out.println("当天光顾的客户列表：");
        for (Customer customer : customerList) {
            System.out.println(customer);
        }
        System.out.println("一天的利润：" + balance);
        isOpen = false;
    }


}

