package work2;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Mia
 * @date 2023/11/1
 */
public class MyAnimalShop implements AnimalShop {
    private double balance;
    private LocalDate closeTime;
    private double profit;
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.profit = balance;
        this.closeTime = null;
        animalList = new ArrayList<>();
        customerList = new ArrayList<>();
    }

    @Override
    public void buy(Animal animal, double buyInPrice) throws InsufficientBalanceException {
        if (closeTime == null) {
            if (balance >= buyInPrice) {
                balance -= buyInPrice;
                this.animalList.add(animal);
                System.out.println("购入动物成功！花费" + buyInPrice + "   剩余余额" + balance);
                System.out.println(animal);
            } else {
                throw new InsufficientBalanceException("余额不足，无法购买");
            }
        } else {
            System.out.println("商店已歇业，无法购入动物");
        }
        System.out.println();
    }

    @Override
    public void treat(Customer person, String buyName) throws AnimalNotFountException {
        System.out.println();
        if (closeTime == null) {
            person.setVisitCount(person.getVisitCount() + 1);
            person.setVisitTime(LocalDate.now());
            System.out.println(person);
            //判断是否为新客
            int index = customerList.lastIndexOf(person);
            if (index == -1) {
                customerList.add(person);
                System.out.println("成功添加新顾客！");
            } else {
                System.out.println("该顾客是老顾客！");
            }
            //在动物列表中根据动物名查找想要购买的动物
            int ind = -1;
            for (int i = 0; i < animalList.size(); i++) {
                if (buyName.equals(animalList.get(i).getName())) {
                    ind = i;
                    break;
                }
            }

            if (animalList.isEmpty()) {
                throw new AnimalNotFountException("店内暂无动物可出售");
            } else if (ind == -1) {
                throw new AnimalNotFountException("店内没有名为" + buyName + "的动物");
            } else {
                System.out.println("成功出售一只小动物！");
                System.out.println(animalList.get(ind));
                balance += animalList.get(ind).getPrice();
                System.out.println("收入+" + animalList.get(ind).getPrice() + " 当前余额为" + balance);
                animalList.remove(animalList.get(ind));
            }
        } else {
            System.out.println("店铺休息中,欢迎下次光临！");
        }
    }

    @Override
    public void close() {
        if (this.closeTime == null) {
            this.closeTime = LocalDate.now();
            System.out.println("今日营业结束！");
            System.out.println("接待了以下顾客：");
            for (int i = 0; i < customerList.size(); i++) {
                System.out.println(customerList.get(i));
            }
            this.profit = balance - profit;
            System.out.println("净收入 " + profit);
        } else {
            System.out.println("店铺未开，无法再次关闭");
        }
        System.out.println();

    }


    public ArrayList<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(ArrayList<Animal> animalList) {
        this.animalList = animalList;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

}
