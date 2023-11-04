import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop{
    private double balance;
    ArrayList<Animal> a = new ArrayList<>();
    ArrayList<Customer> c = new ArrayList<>();
    private boolean business =  true;

    public MyAnimalShop() {
    }

    public MyAnimalShop(double balance, ArrayList<Animal> a, ArrayList<Customer> c) {
        this.balance = balance;
        this.a = a;
        this.c = c;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance<0){
            System.out.println("余额不能为负数");
        }else this.balance = balance;
    }

    public ArrayList<Animal> getA() {
        return a;
    }

    public void setA(ArrayList<Animal> a) {
        this.a = a;
    }

    public ArrayList<Customer> getC() {
        return c;
    }

    public void setC(ArrayList<Customer> c) {
        this.c = c;
    }

    public boolean isBusiness() {
        return business;
    }

    public void setBusiness(boolean business) {
        this.business = business;
    }

    @Override
    public void buyNewAnimal(Animal A) {
        if (balance < A.getPrice()) {
            throw new InsufficientBalanceException("余额不足");
        } else {
            balance = balance - A.getPrice();
            a.add(A);

        }
    }


    @Override
    public void entertainCustomer(Customer C, Animal A) {
        c.add(C);
        if (a.size() == 0) {
            throw new AnimalNotFountException("没有动物可以出售了");
        } else {
            System.out.println(A.toString());
            balance = balance + A.getPrice();
            a.remove(A);
        }


    }

    @Override
    public void outOfBusiness(List<Customer> customers) {
        this.business = false;
        setBusiness(false);
        LocalDate time = LocalDate.now();
        for (int i = 0; i < customers.size(); i++) {
            LocalDate lastTime = customers.get(i).getLastTime();
            if (lastTime.isEqual(time)){
                System.out.println(customers.get(i).toString());
            }
        }
    }
}
