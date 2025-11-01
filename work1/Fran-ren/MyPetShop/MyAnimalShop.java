import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private double moeny;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    private boolean isOpen;
    private LocalDate today;
    private double profit;

    static Scanner sc = new Scanner(System.in);

    public MyAnimalShop() {
    }

    public MyAnimalShop(double moeny, ArrayList<Animal> animalList, ArrayList<Customer> customerList, boolean isOpen,
            LocalDate today, double profit) {
        this.moeny = moeny;
        this.animalList = animalList;
        this.customerList = customerList;
        this.isOpen = isOpen;
        this.today = today;
        this.profit = profit;
    }

    public double getMoeny() {
        return moeny;
    }

    public void setMoeny(double moeny) {
        this.moeny = moeny;
    }

    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public void buyNewAnimal() {
        System.out.print("请选择要买的动物\n1.狗\n2.猫\n3.鸟\n4.猪\n5.兔子\n6.不买了\n请输入您的选择:");
        Animal a;
        while (true) {
            String choice = sc.next();
            switch (choice) {
                case "1" -> a = new Dog();
                case "2" -> a = new Cat();
                case "3" -> a = new Brid();
                case "4" -> a = new Pig();
                case "5" -> a = new Rabbit();
                case "6" -> {
                    return;
                }
                default -> {
                    System.out.print("没有这个选项,请重新选择:");
                    continue;
                }
            }
            break;
        }
        if (moeny - a.price >= 0) {
            moeny -= a.price;
        } else {
            throw new InsufficientBalanceException("余额不足,当前余额为:" + moeny + ",购买需要:" + a.getPrice());
        }
        a.inputPet();
        profit -= a.getPrice();
        animalList.add(a);
    }

    @Override
    public void trateCustomer(Customer customer) {
        customerList.add(customer);
        int size = animalList.size();
        if (size == 0) {
            throw new AnimalNotFountException("店内没有动物可买");
        }
        for (int i = 0; i < size; i++) {
            System.out.print(i + 1 + ":");
            System.out.println(animalList.get(i));
        }
        System.out.print("要卖哪只:");
        int index = sc.nextInt() - 1;
        if (index < size) {
            System.out.println(animalList.get(index).toString());
            double profit = animalList.get(index).getPrice() * 1.5;
            this.profit += profit;
            moeny += profit;
            customer.setVisitStoreCount(customer.getVisitStoreCount() + 1);
            animalList.remove(index);
        }
    }

    @Override
    public void closeShop() {
        int size = customerList.size();
        isOpen = false;
        for (int i = 0; i < size; i++) {
            Customer customer = customerList.get(i);
            if (today.isEqual(customer.getLatesArrivalTimeToStore())) {
                System.out.println(customer.toString());
            }
        }
        System.out.println("今天的利润为:" + profit);
        today.plusDays(1);
    }

    public Customer createCustomer() {
        System.out.print("请输入顾客的名字:");
        String name = sc.next();
        return new Customer(name, 0, today);
    }
}
