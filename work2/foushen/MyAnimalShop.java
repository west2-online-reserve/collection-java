import java.time.LocalDate;
import java.util.ArrayList;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private boolean isOpen;
    private double theDaysProfit;
    private ArrayList<Animal> onSaleAnimals;
    private ArrayList<Customer> customers;

    /**
     * 此方法为MyAnimalShop的构造方法
     *
     * @param balance 代表启动资金
     * @param isOpen
     */
    public MyAnimalShop(double balance, boolean isOpen) {
        this.balance = balance;
        this.isOpen = isOpen;
        this.customers = new ArrayList<>();
        this.onSaleAnimals = new ArrayList<>();
    }

    /**
     * 此方法为MyAnimalShop买入新动物
     * 接受一个Animal类型的参数作为买入动物
     * 同时更新店内余额和当天利润
     * 如果抛出异常则会打印异常信息
     *
     * @param animal
     * @throws AnimalShopIsClosed
     * @throws InsufficientBalanceException
     */
    @Override
    public void buyNewAnimal(Animal animal) throws AnimalShopIsClosed, InsufficientBalanceException {
        try {
            if (!isOpen) {
                throw new AnimalShopIsClosed();
            }
            if (animal.getRestorePrice() > balance) {
                throw new InsufficientBalanceException("余额不足，还差", animal.getRestorePrice() - balance);
            }
        } catch (AnimalShopIsClosed e) {
            System.out.println(e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());
        }

        balance -= animal.getRestorePrice();
        theDaysProfit -= animal.getRestorePrice();
        onSaleAnimals.add(animal);

        System.out.println("进货成功");


    }

    /**
     * 此函数为出售动物函数
     * 接受一个Customer参数和Animal参数作为买动物的顾客个出售的顾客
     * 会更新店内余额和当天利润
     * 会在animals中移除当前动物
     * 更新顾客列表同时更新顾客的最新到店时间为系统时间
     * 输出卖出成功表示此函数成功执行
     *
     * @param customer
     * @param animal
     * @throws AnimalShopIsClosed
     * @throws AnimalNotFountException 如果没找到此动物则抛出此异常并打印异常信息
     */
    @Override
    public void sellAnimal(Customer customer, Animal animal) throws AnimalShopIsClosed, AnimalNotFountException {
        try {
            if (!isOpen) {
                throw new AnimalShopIsClosed();
            }
            if (!onSaleAnimals.contains(animal)) {
                throw new AnimalNotFountException("缺少动物", animal);
            }
        } catch (AnimalShopIsClosed | AnimalNotFountException e) {
            System.out.println(e.getMessage());
        }
        balance += animal.getPrice();
        theDaysProfit += animal.getPrice();
        onSaleAnimals.remove(animal);

        if (!customers.contains(customer)) {
            customer.setLatestVisitShopTime(LocalDate.now());
            customer.setVisitShopTimes(customer.getVisitShopTimes() + 1);
            customers.add(customer);
        }
        System.out.println("卖出成功");

    }

    /**
     * 此函数用来给MyAnimalShop招待新顾客
     * 接受一个Customer代表招待的新顾客
     * 会更新Custom的latestVisitTime为系统时间
     * 会在customs列表中添加此顾客
     * 输出顾客已存在代表customer中已存在此顾客，此时没有进行任何操作
     * 输出成功招待一名顾客代表此函数成功执行
     *
     * @param customer
     * @throws AnimalShopIsClosed
     */
    @Override
    public void serveNewCustomer(Customer customer) throws AnimalShopIsClosed {
        try {
            if (!isOpen) {
                throw new AnimalShopIsClosed();
            }
        } catch (AnimalShopIsClosed e) {
            System.out.println(e.getMessage());
        }

        if (!customers.contains(customer)) {
            customer.setVisitShopTimes(customer.getVisitShopTimes() + 1);
            customer.setLatestVisitShopTime(LocalDate.now());
            customers.add(customer);
            System.out.println("成功招待一名顾客");
            return;
        }
        System.out.println("该顾客已存在");

    }

    /**
     * 此函数用于关闭宠物店
     * 同时输出当天的利润和当天到店的顾客的信息
     * 将isOpen改为false
     * 将当天的利润清零
     */
    @Override
    public void close() {
        if (!isOpen) {
            System.out.println("商店已经关闭");
            return;
        }

        System.out.println("今天的利润为：" + theDaysProfit);
        //清零当天利润
        theDaysProfit = 0;

        for (Customer customer : customers) {
            if (customer.getLatestVisitShopTime().isEqual(LocalDate.now())) {
                System.out.println(customer);
            }
        }

        isOpen = false;

        System.out.println("商店已经关闭");
    }

    /**
     *  开店函数
     */
    @Override
    public void open() {
        isOpen = true;
        System.out.println("商店已开启");
    }

}
