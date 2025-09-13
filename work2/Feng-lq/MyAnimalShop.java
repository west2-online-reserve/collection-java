package work2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author FAN
 */
public class MyAnimalShop implements AnimalShop {
    private double balance = 10000;
    private final ArrayList<AbstractAnimal> animals = new ArrayList<>();
    private final ArrayList<Customer> customers = new ArrayList<>();
    /**
     * 是否关店
     */
    private Boolean close = true;
    /**
     * 营业额
     */
    private double turnover = 0;
    /**
     * 用于输入，判断交互
     */
    private final Scanner sc = new Scanner(System.in);
    /**
     * 开店初始资金
     */
    private final double money = balance;


    @Override
    public void buyNewAnimal(AbstractAnimal animal) {
        if (!close) {
            //方法实现购买动物功能
            try {
                if (balance >= animal.price) {
                    animals.add(animal);
                    balance -= animal.price;
                    System.out.println("-------购买成功-------");
                    System.out.printf(
                            "余额还剩%s\t店面现有动物%d只%n", balance, animals.size());
                    System.out.println();
                } else {
                    //余额不足，抛出错误
                    throw new InsufficientBalanceException("余额不足，购买失败");
                }
            } catch (InsufficientBalanceException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        } else {
            System.out.println("店铺已关门，无法进行该操作");
            System.out.println();
        }


    }

    @Override
    public void entertainClients(Customer customer) {
        if (!close) {
            //检查顾客是否为新顾客，录入信息
            if ((!customers.contains(customer) && customer.getCount() == 0)
                    && customer.getCount() == 0) {
                //不在顾客列表，且来的次数为0，认证为新客户
                System.out.println("欢迎新客户" + customer.getName());
                customer.setTime(LocalDate.now());
                customer.setCount(customer.getCount() + 1);
                customers.add(customer);
            } else if (!customers.contains(customer)) {
                //不在顾客列表，但来的次数不为0，虽然不是新客户，但是要加入顾客列表
                customer.setTime(LocalDate.now());
                customer.setCount(customer.getCount() + 1);
                customers.add(customer);
                System.out.println("顾客" + customer.getName() + "\t最近一次到来的日期为"
                        + customer.getTime() + "\t共到店" + customer.getCount() + "次");
            } else {
                customer.setTime(LocalDate.now());
                customer.setCount(customer.getCount() + 1);
                //打印顾客信息
                System.out.println("顾客" + customer.getName() + "\t最近一次到来的日期为"
                        + customer.getTime() + "\t共到店" + (customer.getCount() + 1) + "次");

            }

            //打印动物列表
            for (int i = 0; i < animals.size(); i++) {
                System.out.println("序号:" + i + "\t" + animals.get(i));
            }

            System.out.println("要售出动物的序号为：");
            int index = sc.nextInt();

            try {
                for (int i = 0; i < animals.size(); i++) {
                    if (i == index) {
                        if (animals.get(i) instanceof ChineseRuralDog) {
                            //若为中华田园犬，执行疫苗注射方法，
                            vaccineInjected((ChineseRuralDog) animals.get(i));
                        }
                        //售价为成本价的1.2倍
                        balance += animals.get(i).price * 1.2;
                        turnover += animals.get(i).price * 1.2;
                        animals.remove(animals.get(i));
                        System.out.println("-------售出成功-------");
                        System.out.println();
                        break;
                    } else if (i == animals.size() - 1) {
                        throw new AnimalNotFountException("未找到该动物，售出失败");
                    }
                }
            } catch (AnimalNotFountException e) {
                System.out.println(e.getMessage());
            }

        } else {
            System.out.println("店铺已关门，无法进行该操作");
            System.out.println();
        }


    }

    @Override
    public void isClose() {
        close = true;
        System.out.println("-----今日顾客-----");
        for (Customer customer : customers) {
            if (customer.getTime().equals(LocalDate.now())) {
                System.out.println(customer);
            }
        }
        System.out.println("--------------------");
        System.out.println("今日营业额为：" + turnover);
        System.out.println("今日利润为：" + (balance - money));
        System.out.println();

    }

    /**
     * 开始营业
     * 打印店铺动物列表及现有余额
     */
    public void isOpen() {
        close = false;
        System.out.println("-------开始营业-------");
        //打印动物列表
        if (animals.isEmpty()) {
            System.out.println("店内暂无动物，请及时购买动物");
        } else {
            System.out.println("店内现有动物");
            for (int i = 0; i < animals.size(); i++) {
                System.out.println("序号:" + i + "\t" + animals.get(i));
            }
        }
        System.out.println("目前余额为：" + balance);
        System.out.println();
    }

    /**
     * 判断所售中华田园犬是否注射疫苗
     * 若未打，询问顾客是否要打，一针20
     *
     * @param chineseRuralDog
     */
    public void vaccineInjected(ChineseRuralDog chineseRuralDog) {
        if (!chineseRuralDog.getVaccineInjected()) {
            System.out.println(
                    chineseRuralDog.name + "未打疫苗，顾客是否需要？(y/n)");
            String vaccineInjected = sc.next();
            if ("y".equals(vaccineInjected)) {
                //更新小狗疫苗状态
                chineseRuralDog.setVaccineInjected(true);
                balance += 20;
                turnover += 20;
            }
        }

    }

}
