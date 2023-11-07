import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 宠物店的实现
 *
 * @author AGoodYear
 * @date 2023/11/4
 */
public class MyAnimalShop implements AnimalShop{
    private double balance;
    /**
     * ArrayList存放已有的动物
     */
    private ArrayList<AbstractAnimal> animalList;
    /**
     * ArrayList存放顾客列表
     */
    private ArrayList<Customer> customerList;
    private boolean isOpen;
    /**
     * 储存当日日期
     */
    private LocalDate nowTime;
    /**
     * 储存当日利润
     */
    private double profit;
    /**
     * 当日是否接待了顾客
     */
    private boolean customerReceipted;

    /**
     * 测试用方法，作弊改变余额
     *
     * @param num 要增加的余额
     */
    public void cheat(double num) {
        balance += num;
    }

    public MyAnimalShop(double balance) {
        animalList = new ArrayList<>();
        customerList = new ArrayList<>();
        this.balance = balance;
        nowTime = LocalDate.now();
        customerReceipted = false;
        isOpen = false;
        profit = 0;
    }

    /**
     * 宠物店买入动物
     *
     * @param a 要买入的动物
     * @throws InsufficientBalanceException 如果余额不足
     */
    @Override
    public void buyNewAnimal(AbstractAnimal a) {
        // 判断余额是否能够买下动物
        if (a.getPrice() > balance) {
            // 抛出余额不足异常，附带余额和动物价格信息
            throw new InsufficientBalanceException(balance, a.getPrice());
        } else {
            animalList.add(a);
            balance -= a.getPrice();
            System.out.println("已买入新动物" + a.getName());
        }
    }

    /**
     * 宠物店招待顾客
     *
     * @param c 要招待的顾客
     * @throws AnimalNotFountException 如果宠物店内没有动物
     */
    @Override
    public void receiptCustomer(Customer c) {
        // 检查宠物店是否营业
        if (!isOpen) {
            System.out.println("暂未营业！");
        } else {
            // 储存当前顾客在顾客列表中的位置
            int index = 0;
            // 当前顾客是否已经存在
            boolean customerNotExist = true;
            // 搜索当前顾客是否存在
            for (int i=0; i<customerList.size(); i++) {
                if (customerList.get(i) == c) {
                    // 位置改为表中位置
                    index = i;
                    customerNotExist = false;
                }
            }
            // 若不存在则加入列表
            if (customerNotExist){
                customerList.add(c);
                index = customerList.size()-1;
            }
            // 若宠物店内无动物则抛出异常
            if (animalList.isEmpty()) {
                throw new AnimalNotFountException();
            } else {
                // 更新顾客相关信息
                customerReceipted = true;
                customerList.get(index).plusArrivalTime();
                customerList.get(index).setLatestArrival(nowTime);
                System.out.println("欢迎光临," + customerList.get(index).getName() + "!请选择要进行的操作：");
                System.out.print("输入\"1\"出售动物，其他键退出");
                // 等待用户选择要进行的操作
                int selected;
                Scanner scanner = new Scanner(System.in);
                selected = scanner.nextInt();
                if (selected == 1) {
                    // 输出当前拥有的动物
                    System.out.println("以下为本店目前拥有的动物");
                    for (int i=0; i<animalList.size(); i++) {
                        System.out.println("有一只叫" + animalList.get(i).getName() + "的" + animalList.get(i).getType());
                    }
                    System.out.println("输入要购买的动物的编号，输入-1退出");
                    while (true) {
                        // 防止输入不合法
                        selected = scanner.nextInt();
                        if (selected != -1 && selected <= animalList.size()) {
                            // 更新相关信息，移除动物
                            System.out.println("以下为售出的动物信息：");
                            System.out.println(animalList.get(selected-1).toString());
                            balance += (animalList.get(selected-1).getPrice() + animalList.get(selected-1).getProfit());
                            profit += animalList.get(selected-1).getProfit();
                            animalList.remove(selected-1);
                            System.out.println("交易结束，欢迎下次光临！");
                            // 将更新后的信息传给引用参数
                            c = customerList.get(index);
                            break;
                        } else if (selected > animalList.size()) {
                            System.out.println("参数非法，请重新选择");
                        } else {
                            break;
                        }
                    }
                }
            }
        }

    }

    /**
     * 关闭宠物店并输出当日经营信息
     */


    @Override
    public void closeShop() {
        if (customerReceipted) {
            System.out.println("以下为当天到店的顾客：");
            for (int i=0; i<customerList.size(); i++) {
                if (customerList.get(i).getLatestArrival() == nowTime) {
                    System.out.println(customerList.get(i).toString());
                }
            }
        } else {
            System.out.println("当天无到店顾客。");
        }
        customerReceipted = false;
        System.out.println("今日利润：" + profit);
        System.out.println("余额：" + balance);
        profit = 0;
        isOpen = false;
    }

    /**
     * 宠物店开业
     */
    public void openShop() {
        System.out.println("开门迎客");
        isOpen = true;
        profit = 0;
        nowTime = LocalDate.now();
    }
}
