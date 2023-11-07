import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animalList;



    private List<Customer> customerList;
    private boolean isOperating;
    private double profit;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOperating = true;
    } public List<Customer> getCustomerList() {
        return customerList;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        try {
            if (balance >= animal.price) {
                animalList.add(animal);
                balance -= animal.price;
                if(animal.price==100) {
                    System.out.println("卖入的宠物为狗：" + animal.name + "," + animal.age + "." + animal.gender);
                }else {
                    System.out.println("卖入的宠物为猫：" + animal.name + "," + animal.age + "." + animal.gender);
                }
            } else {
                throw new InsufficientBalanceException("没钱！！！");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e);
        }
    }
    @Override
    public void serveCustomer(Customer customer, int index) throws AnimalNotFoundException {
        try {
            if (!isOperating) {
                System.out.println("关门了");
                return;
            }
            if (animalList.isEmpty()) {
                throw new AnimalNotFoundException("没有宠物可以卖了！！！");
            }
            Animal animal = animalList.remove(index);
            System.out.println("出售的宠物" + animal.toString());
            profit += animal.price;
            if(customerList.contains(customer)){
                customer.setVisitCount(customer.getVisitCount() + 1);
                customer.setLatestVisitDate(LocalDate.now());
            }
            else {
                customerList.add(customer);
                customer.setVisitCount(customer.getVisitCount() + 1);
                customer.setLatestVisitDate(LocalDate.now());
            }

            balance += animal.price;
        } catch (AnimalNotFoundException e) {
            System.out.println(e);
        }
    }

    @Override
    public void closeShop() {
        isOperating = false;
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
        System.out.println("今天卖宠物赚了多少：" + profit);
    }
}
