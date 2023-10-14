import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    protected double restMoney;//余额
    protected boolean isOpen;//是否营业
    protected ArrayList<Animal> animals;
    protected ArrayList<Customer> customers;
    protected double theDayProfit;//当天的利润

    public MyAnimalShop(double buildMoney) {
        animals = new ArrayList<Animal>();
        customers = new ArrayList<Customer>();
        restMoney = buildMoney;
    }

    @Override
    public void buyNewAnimal(Animal animal) throws AnimalShopIsClosed {
        //异常处理
        if (!isOpen) {
            throw new AnimalShopIsClosed();
        }
        if (animal.getRestorePrice() > restMoney) {
            throw new InsufficientBalanceException(animal.getRestorePrice() - restMoney, "余额不足");
        }


        animals.add(animal);
        restMoney -= animal.getRestorePrice();
        theDayProfit -= animal.getRestorePrice();
        System.out.println("已购买动物\n" + animal);
    }

    @Override
    public void addCustomer(Customer customer) {
        //判断该顾客是否在列表里
        if (!customers.contains(customer)) {
            //添加顾客并设置顾客最后一次到店时间
            customers.add(customer);
            customer.latestComeShopTime = LocalDate.now();
            System.out.println("成功添加顾客\n" + customer);
        }
    }

    public void soldAnimal(Animal animal, Customer customer) throws AnimalShopIsClosed, InsufficientBalanceException {
        //异常处理
        if (!isOpen) {
            throw new AnimalShopIsClosed();
        }
        if (!animals.contains(animal)) {
            throw new AnimalNotFountException("未在在售列表中找到", animal);
        }

        //顾客购买动物，在顾客拥有的动物列表中添加该animal
        customer.buyAnimal(animal);
        restMoney += animal.getPrice();
        theDayProfit += animal.getPrice();//利润为购买的钱减去进货的钱

        //如果不在顾客列表，加入顾客列表
        if (!customers.contains(customer)) {
            addCustomer(customer);//addCustomer中已经设置的到店时间，故不用修改时间
        }
        //否则直接改变顾客最新到店的时间
        else {
            customer.latestComeShopTime = LocalDate.now();

        }
        //使顾客的shopTimes加1
        customer.increaseShopTimes();

        //从在售动物列表里移除该动物
        animals.remove(animal);
        System.out.println("购买成功");
    }

    @Override
    public void closeShop() {
        if (!isOpen) {
            System.out.println("已关闭，无需重复执行关闭操作");
            return;
        }
        isOpen = false;

        System.out.println("今天的利润为:" + theDayProfit);

        System.out.println("今天到店的顾客如下");
        for (Customer theCustomer : customers) {
            if (theCustomer.latestComeShopTime.isEqual(LocalDate.now())) {
                System.out.println(theCustomer + "\n");
            }

        }

        System.out.println("关店成功");

    }

    public void openShop() {
        if (isOpen) {
            System.out.println("已开启，无需重复执行开启操作");
            return;
        }
        //清空前一天的利润
        theDayProfit = 0;
        isOpen = true;
        System.out.println("已开启");
    }
}

