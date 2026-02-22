import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private ArrayList<Animal> list;
    private ArrayList<Customer> listMemory;
    private boolean isWorking;
    private double profitDay;

    public MyAnimalShop(double initBalance) {
        this.balance = initBalance;
        this.list = new ArrayList<>();
        this.listMemory = new ArrayList<>();
        this.isWorking = true;
        this.profitDay = 0;
    }

    public MyAnimalShop() {
    }

    @Override
    public void bugNewAnimal(Animal animal) {
        if (animal.getPrice() > balance) {
            System.out.println("余额不足");
            throw new InsufficientBalanceException("宠物店资金不足");
        } else {
            this.balance -= animal.getPrice();
            list.add(animal);
            this.profitDay -= animal.getPrice();
            System.out.println("成功买入新宠物" + animal.toString());
        }
    }

    @Override
    public void serveCustomer(Customer customer) {
        if (!isWorking) {
            System.out.println("宠物店没有营业");
            return;
        }
        listMemory.add(customer);
        System.out.println("正在招待顾客" + customer.toString());
        if (list.isEmpty()) {
            throw new AnimalNotFountException("目前没有宠物可以出售");
        }
        Animal soldAnimal = this.list.get(0);//这里不知道顾客想要买什么动物默认买动物列表的第一个动物
        list.remove(0);
        balance += soldAnimal.getPrice();
        profitDay += soldAnimal.getPrice();
        System.out.println("成功卖出" + soldAnimal.toString());
    }

    @Override
    public void closeShop() {
        isWorking = false;
        System.out.println("关门咯");
        LocalDate today = LocalDate.now();
        boolean isHasCustomer = false;
        for (Customer i : listMemory) {
            if (i.getLatestVisit().equals(today)) {
                isHasCustomer = true;
                break;
            }
        }
        if (isHasCustomer == false) {
            System.out.println("今天没有顾客光顾");
        } else {
            System.out.println("今天光顾的顾客名单如下：");
            for (Customer i : listMemory) {
                if (i.getLatestVisit().equals(today)) {
                    System.out.println(i);
                }
            }
        }
        System.out.println("今天的利润为：" + profitDay + "元");
        profitDay = 0;
    }
}
