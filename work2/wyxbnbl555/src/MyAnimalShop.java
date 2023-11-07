import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop{
    private double balance;
    private List<Animal> animalList = new ArrayList<>();
    private List<Customer> customerList = new ArrayList<>();
    private boolean business;
    private double profit = 0;

    public MyAnimalShop() {
    }

    public MyAnimalShop(double balance,boolean business) {
        this.balance = balance;
        this.business = business;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance<0){
            System.out.println("余额不能为负数");
        }else this.balance = balance;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public boolean isBusiness() {
        return business;
    }

    public void setBusiness(boolean business) {
        this.business = business;
    }

    @Override
    public void buyNewAnimal(Animal A) {
        try {
            if (balance < A.getPrice()) {
                throw new InsufficientBalanceException("余额不足");
            } else {
                balance = balance - A.getPrice();
                animalList.add(A);

            }
        }catch (InsufficientBalanceException e){
            System.out.println(e);
        }
    }


    @Override
    public void entertainCustomer(Customer C) {
        try {
            if (!business) {
                System.out.println("关门了，明天再来");
                return;
            }
            customerList.add(C);
            C.setVisitNumber(C.getVisitNumber() + 1);
            C.setLastTime(LocalDate.now());
            if (animalList.size() == 0) {
                throw new AnimalNotFountException("没有动物可以出售了");
            } else {
                System.out.println("现在有这些动物");
                for (int i = 0; i < animalList.size(); i++) {
                    System.out.println((i+1)+" "+animalList.get(i));
                }
                System.out.println("请输入要买的动物");
                Scanner sc = new Scanner(System.in);
                String r = sc.next();
                if ((Integer.parseInt(r) - 1) < animalList.size()) {
                    Animal animal = animalList.get(Integer.parseInt(r) - 1);
                    System.out.println(animal);
                    animalList.remove(animal);
                    profit += animal instanceof ChinesePastoralDog ? 100 : (animal instanceof Cat ? 200 : 300);
                } else {
                    System.out.println("没有这个动物");
                }
            }
        } catch (AnimalNotFountException e){
            System.out.println(e);
        }



    }

    @Override
    public void outOfBusiness(List<Customer> customers) {
        System.out.println("一天的顾客有："+customers.size());
        for(int i=0;i< customerList.size();i++){
            System.out.println(customers.get(i));
        }
        System.out.println("一天的利润有"+profit);

    }
}
