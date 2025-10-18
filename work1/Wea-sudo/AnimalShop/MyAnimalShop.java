package example;
import java.util.ArrayList;
import java.time.LocalDate;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private ArrayList<Animal> myAnimal = new ArrayList<>();
    private ArrayList<Customer> myCustomer = new ArrayList<>();
    private ArrayList<Bill> myBillToday = new ArrayList<>();
    private double profitToday = 0;
    private boolean isOnBusiness;

    public MyAnimalShop() {
        balance = 0;
        isOnBusiness = false;
    }

    public MyAnimalShop(double balance, boolean isOnBusiness) {
        this.balance = balance;
        this.isOnBusiness = isOnBusiness;
    }

    public void showListToday() {
        System.out.println("今天的订单:");
        for (int i = myBillToday.size() - 1 ; i >= 0 ; i--) {
            System.out.println(myBillToday.get(i).toString());
            myBillToday.remove(i);
        }
        System.out.println("今天的总利润:" + profitToday);
    }

    public void open() {
        isOnBusiness = true;
    }

    public void printCustomerHasVisit() {
        System.out.println("历史顾客:");
        for (int i = 0; i < myCustomer.size(); i++) {
            System.out.println(myCustomer.get(i).toString());
        }
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if(!isOnBusiness) {
            System.out.println("本店已打烊");
            return;
        }
        if(balance >= animal.getPrice()) {
            balance -= animal.getPrice();
            profitToday -= animal.getPrice();
            myAnimal.add(animal);
        }else {
            throw new InsufficientBalanceException("余额不足");
        }

    }

    @Override
    public void solicitCustomer(Customer customer, Class<? extends Animal> animalType, double price, LocalDate localDate) throws AnimalNotFoundException {
        if(!isOnBusiness) {
            System.out.println("本店已打烊");
            return;
        }

        boolean hasAnimalStock = false;

        for (int i = 0; i < myAnimal.size() && !hasAnimalStock; i++) {
            if(animalType.isInstance(myAnimal.get(i))) {

                if(customer.getCountOfVisitStore() <= 0) {
                    myCustomer.add(customer);
                }
                customer.visit();
                balance += price;
                profitToday += price;
                myBillToday.add(new Bill(customer, myAnimal.get(i), price, localDate));
                myAnimal.remove(i);
                customer.setLatestDate(localDate);
                hasAnimalStock = true;

            }
        }
        if(!hasAnimalStock) {
            throw new AnimalNotFoundException("库存缺少" + animalType.getSimpleName());
        }


    }

    @Override
    public void close() {

        showListToday();

        profitToday = 0;
        isOnBusiness = false;
    }
}
