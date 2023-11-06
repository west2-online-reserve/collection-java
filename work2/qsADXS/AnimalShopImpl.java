import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class AnimalShopImpl implements AnimalShop {

    private boolean businessStatus;
    private double balance;
    private double balanceOfToday;
    private List<Animal> allowSell;
    private List<Customer> customers;
    private LocalDate today;

    public AnimalShopImpl(double balance) {
        this.balance = balance;
        allowSell = new ArrayList<>();
        customers = new ArrayList<>();
        businessStatus = false;
        this.balanceOfToday = 0;
    }

    @Override
    public void buyNewAnimals(Animal aAnimal, int number) {

        try {
            if (balance >= aAnimal.getPurchasingCost() * number) {
                for (int i = 0; i < number; i++) {
                    allowSell.add(aAnimal);
                }
                System.out.println("进货" + number + "只");
                System.out.println("成功进货：" + aAnimal.toString());
                balance -= aAnimal.getPurchasingCost() * number;
                System.out.println("余额" + balance);
            } else {
                throw new InsufficientBalanceException("余额不足");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void entertainCustomer() {
        if (businessStatus) {
            Scanner in = new Scanner(System.in);
            System.out.println("输入顾客名称：");
            String name = in.nextLine();
            Customer customer = null;
            boolean isFind = false;
            for (Customer customersList : customers) {
                if (Objects.equals(customersList.getName(), name)) {
                    isFind = true;
                    customer = customersList;
                    break;
                }
            }
            if (!isFind) {
                customer = new Customer(name);

                customers.add(customer);
            }
            customer.arrive();

            System.out.println("顾客名" + name);
            try {
                if (allowSell.isEmpty()) {
                    throw new AnimalNotFountException("店内没有动物可以购买");
                }
                System.out.println("当前可购买的宠物:");
                for (int i = 0; i < allowSell.size(); i++) {
                    System.out.println((i + 1) + allowSell.get(i).toString() + "\n");
                }
                System.out.println("请输入要购买宠物的编号，如果放弃购买输入0");
                int n = in.nextInt() - 1;

                if (n >= 0 && n < allowSell.size()) {
                    balance += allowSell.get(n).getPrice();
                    balanceOfToday += allowSell.get(n).getPrice();
                    System.out.println("成功购买：\n" + allowSell.remove(n));
                } else {
                    throw new AnimalNotFountException("没有找到该动物");
                }
            } catch (AnimalNotFountException e) {
                System.out.println(e.toString());
            }
        } else {
            System.out.println("未营业，无法招待");
        }
    }

    @Override
    public void close() {
        businessStatus = false;
        System.out.println("进日营业额：" + balanceOfToday);
        balanceOfToday = 0;
    }

    public void open() {
        businessStatus = true;
        today = LocalDate.now();
    }
}
