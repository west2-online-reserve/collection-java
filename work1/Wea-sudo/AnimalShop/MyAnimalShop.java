package example;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private ArrayList<Animal> myAnimal = new ArrayList<>();
    private ArrayList<Customer> myCustomer = new ArrayList<>();
    private ArrayList<Bill> myBillToday = new ArrayList<>();
    private int profitToday = 0;
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
            myAnimal.add(animal);
        }else {
            throw new InsufficientBalanceException("余额不足");
        }

    }

    @Override
    public void SolicitCustomer(Customer customer, String className, int price, LocalDate localDate) throws AnimalNotFountException {
        if(!isOnBusiness) {
            System.out.println("本店已打烊");
            return;
        }

        boolean hasAnimalStock = false;

        for (int i = 0; i < myAnimal.size() && !hasAnimalStock; i++) {
            if(myAnimal.get(i).getClassName().equals(className)) {

                if(customer.getCountOfVisitStore() <= 0) {
                    myCustomer.add(customer);
                }
                customer.visit();
                balance += price;
                profitToday += price - myAnimal.get(i).getPrice();
                myBillToday.add(new Bill(customer, myAnimal.get(i), price, localDate));
                myAnimal.remove(i);
                customer.setLatestDate(localDate);
                hasAnimalStock = true;

            }
        }
        if(!hasAnimalStock) {
            throw new AnimalNotFountException("库存缺少" + className);
        }


    }

    @Override
    public void close() {
        showListToday();

        profitToday = 0;
        isOnBusiness = false;
    }
}
