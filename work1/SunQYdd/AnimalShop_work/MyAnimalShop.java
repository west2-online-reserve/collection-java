import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop{
    private double money;
    private boolean work;
    private double profit;

    private ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Customer> customers = new ArrayList<>();

    MyAnimalShop(){
        this.money = 0;
        this.work = true;
        this.profit = 0;
        animals.add(new Rabbit("大大", 1, '公', 20000));
        animals.add(new Zhonghuadog("黄毛", 18, '母'));
        animals.add(new Cat("阿呆", 5, '母'));
    }

    MyAnimalShop(double money){
        this.money = money;
        this.work = true;
        this.profit = 0;
        animals.add(new Rabbit("大大", 1, '公', 20000));
        animals.add(new Zhonghuadog("黄毛", 18, '母'));
        animals.add(new Cat("阿呆", 5, '母'));
    }

    @Override
    public void addAnimal(Animal animal) {
        if(animal.getPrice() <= money) {
            this.animals.add(animal);
            this.money -= animal.getPrice();
            this.profit -= animal.getPrice();
        } else {
            throw new InsufficientBalanceException("余额不足");
        }
    }


    @Override
    public void addCustomer(Customer customer, Animal animal) {
        customers.remove(customer);
        customer.setCnt(customer.getCnt() + 1);
        customers.add(customer);

        if(animals.contains(animal)) {
            this.animals.remove(animal);
            this.money += animal.getPrice();
            this.profit += animal.getPrice();
            System.out.println(animal.toString());
        } else {
            throw new AnimalNotFoundException("店内没有这只动物");
        }
    }

    @Override
    public void close() {
        System.out.println("歇业啦！！");
        this.work = false;

        for(Customer customer : customers) {
            System.out.println(customer.toString());
        }
        System.out.println("一天的利润为：" + this.profit + "元!");
    }
}
