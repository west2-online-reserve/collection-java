import javax.naming.InsufficientResourcesException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class MyAnimalShop implements AnimalShop{
    private double balance;
    private List<Animal> animalList = new ArrayList<>();
    private List<Customer> customerList= new ArrayList<>();
    private boolean isClosed;
    private double profit = 0;

    public MyAnimalShop(double balance, boolean isClosed) {
        this.balance = balance;
        this.isClosed = isClosed;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
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

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }


    @Override
    public void purchaseAnimal(Animal animal) {
        try{
            if(balance >= animal.price){
                balance -= animal.price;
                animalList.add(animal);
                System.out.println("购买成功！我的宠物店有" + animalList.size() + "只宠物哦~~,此时余额为：" + balance );
            } else {
                throw new InsufficientBalanceException("余额不足...想想自己的问题，钱都去哪了");
            }
        } catch (InsufficientBalanceException e){
            System.out.println(e);
        }
    }

    @Override
    public void treatCustomer(Customer customer) {
        try {
            if (isClosed) {
                System.out.println("关门啦，明天来哦~");
                return;
            }
            customerList.add(customer);
            customer.setVisitTimes(customer.getVisitTimes() + 1);
            customer.setLatestVisitDate(LocalDate.now());
            if(animalList.size() == 0){
                throw new AnimalNotFoundException("sorry，店内没有动物~");
            } else {
                int num = (int) (Math.random() * animalList.size());
                Animal animal = animalList.get(num);
                animalList.remove(num);
                profit += animal instanceof ChineseFieldDog ? 100 : (animal instanceof Cat ? 200 : 6666.6);
                System.out.println("出售了：");
                System.out.println(animal);

            }

        } catch (AnimalNotFoundException e){
            System.out.println(e);
        }
    }

    @Override
    public void rest() {

        System.out.println("一天的顾客有：");
        Iterator iter = customerList.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("一天的利润有：");
        System.out.println(profit);
    }
}
