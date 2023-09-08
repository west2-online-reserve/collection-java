import java.util.LinkedList;
import java.util.List;

public class MyAnimalShop implements AnimalShop{
    protected double count;
    List<Animal> AnimalList = new LinkedList<>();
    List<Customer> CustomerList = new LinkedList<>();
    boolean isClosed;

    private double profit = 0;

    public MyAnimalShop(double count, boolean isClosed) {
        this.count = count;
        this.isClosed = isClosed;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    @Override
    public void buy(Animal animal) {
        try {
            if(isClosed){
                System.out.println("宠物店已经休息了.\n");
                return;
            }
            System.out.println("-----------------");
            System.out.println(count);
            System.out.println(animal.price);
            System.out.println("-----------------");
            if (count >= animal.price) {
                count -= animal.price;
                AnimalList.add(animal);
                System.out.println("购买成功!目前宠物店中有" + AnimalList.size() + "只宠物，购买的新宠物是：" + animal);
            } else {
                throw new InsufficientBalanceException("余额不足");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void serve(Customer customer) {
        System.out.println();
        try {
            if(isClosed){
                System.out.println("宠物店已打烊.\n");
                return;
            }
            if (!AnimalList.isEmpty()) {
                CustomerList.add(customer);
                System.out.println(customer.name + "," + "您所购买的宠物为：");
                int number = (int) (Math.random() * AnimalList.size());
                Animal animal = AnimalList.get(number);
                AnimalList.remove(number);
                profit += animal instanceof Cat ? 300 : 200;
                System.out.println(animal);
                customer.times++;
            } else {
                throw new AnimalNotFoundException("抱歉，当前没有可以宠物可以购买");
            }
        } catch (AnimalNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void close() {
        System.out.println("今日光顾的顾客有：");
        for (Customer it : CustomerList) System.out.println(it.toString());
        System.out.print("今日的利润为：");
        System.out.println(profit);
        count += profit;
        profit = 0;
    }
}
