import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private ArrayList<Animal> animalArrayList;
    private ArrayList<Customer> customerArrayList;
    //是否正在营业
    private boolean isClosed;
    private double profit = 0;

    MyAnimalShop() {
    }

    ;

    MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalArrayList = new ArrayList<Animal>();
        this.customerArrayList = new ArrayList<Customer>();
        this.isClosed = false;
    }

    public void showProfit() {
        System.out.println("当前宠物店的余额为：" + profit);
    }

    public double getBalance() {
        return balance;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void showbalance() {
        System.out.println("宠物店的余额为：" + balance);
    }

    public void showAnimal() {

        System.out.println("宠物店现有的宠物有:");
        for (int i = 0; i < animalArrayList.size(); i++) {
            System.out.println("序号: " + i);
            System.out.println(animalArrayList.get(i).toString());
            System.out.println();
        }
    }

    public void buyAnimal() {
        System.out.println("客户要购买的动物序列为：");
        Scanner order = new Scanner(System.in);
        int orderNumber = order.nextInt();

        System.out.println("卖出的动物为:\n" + animalArrayList.get(orderNumber).toString());
        //余额、利润都增加
        balance += animalArrayList.get(orderNumber).animalPrice;
        balance += animalArrayList.get(orderNumber).animalPrice;
        animalArrayList.remove(orderNumber);
    }

    public void showCustomer() {
        System.out.println("宠物店的客户有:");
        for (int i = 0; i < customerArrayList.size(); i++) {
            System.out.println(customerArrayList.get(i).toString());
            System.out.println();
        }
    }

    @Override
    //实现接口中的方法
    //1.买入动物
    public void buyNewAnimal(Animal animal) {
        try {
            //如果要购买的动物价钱已经超过了余额
            if (animal.animalPrice <= balance) {
                animalArrayList.add(animal);
                balance -= animal.animalPrice;
                System.out.println("购买成功！宠物店目前已经有" + animalArrayList.size() + "只宠物了！");
            }
            //购买成功
            else {
                throw new InsufficientBalanceException("宠物店余额不足......");
            }
        } catch (InsufficientBalanceException exception) {
            System.out.println(exception.toString());
        }
    }

    @Override
    //实现接口中的方法
    //2.招待客户
    public void entertainCustomer(Customer customer) {
        try {
            if (isClosed) {
                System.out.println("关门了，请明天再来~");
                return;
            }

            if (customerArrayList.isEmpty()) {
                customerArrayList.add(customer);
            } else {
                boolean judge = false;
                for (int i = 0; i < customerArrayList.size(); i++) {
                    if (customer.getCustomerName() == customerArrayList.get(i).getCustomerName()) {
                        customerArrayList.get(i).addTimes();
                        customerArrayList.get(i).setDate();
                        judge = true;
                        break;
                    }
                }
                if (!judge) {
                    customerArrayList.add(customer);
                }
            }

            if (animalArrayList.isEmpty()) {
                throw new AnimalNotFountException("\nsorry,店内已经没有动物了~");
            } else {
                showAnimal();
                buyAnimal();
            }
        } catch (AnimalNotFountException exception) {
            System.out.println(exception.toString());
        }

    }

    @Override
    //实现接口中的方法
    //3.歇业
    public void closeShop() {
        System.out.println("一天的顾客有：");
        Iterator iter = customerArrayList.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
        System.out.println("一天的利润有：" + profit);
    }
}
