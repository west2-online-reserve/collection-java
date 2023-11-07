/**
 * 宠物店实现
 *
 * @author pany0593
 * @date 2023/11/5
 */

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {

    private double balance;
    private double profit;
    private final List<AbstractAnimal> animalsList;
    private final List<Customer> customersList;
    private boolean isClosed;
    private final double profitRate = 0.4;

    public boolean getClosed() {
        return this.isClosed;
    }

    public void setClosed(boolean isClosed) {
        this.isClosed = isClosed;
    }

    public double getProfit() {
        return this.profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public double getBalance() {
        return this.balance;
    }

    private void setBalance(double balance) {
        this.balance = balance;
    }

    MyAnimalShop(double balance) {
        this.setBalance(balance);
        this.setClosed(false);
        this.setProfit(0);
        this.animalsList = new ArrayList<>();
        this.customersList = new ArrayList<>();
    }


    @Override
    public String toString() {
        return "余额：" + this.getBalance() + '\n' +
                "今日利润：" + this.getProfit() + '\n' +
                "是否关店：" + (this.getClosed() ? "是" : "否") + "\n";
    }

    @Override
    public double buyNewAnimal(AbstractAnimal animal) throws InsufficientBalanceException {
        if (this.animalsList.contains(animal)) {
            System.out.println("已经购买过该宠物:\n"+animal.toString());
        } else {
            //进货价为标价一半 profitRate 利润率为0.5
            if (this.balance >= animal.price * (1 - profitRate)) {
                this.animalsList.add(animal);
                this.balance -= animal.price * (1 - profitRate);
                System.out.println("成功购买宠物：\n" + animal.toString());
            } else {
                throw new InsufficientBalanceException("购买宠物失败：当前余额:" + this.balance +
                        " 宠物进价:" + animal.getPrice() * (1 - profitRate) + '\n');
            }
        }
        return 0;
    }

    @Override
    public void serveCustomer(Customer customer) throws AnimalNotFountException {
        this.customersList.add(customer);
        customer.setArriveTimes(customer.getArriveTimes() + 1);
        customer.setLatestArriveTime(LocalDate.now());

        if (this.animalsList.isEmpty()) {
            throw new AnimalNotFountException("购买失败：当前店内没有更多宠物" + '\n');
        } else {
            //直接卖出最早购买的宠物
            System.out.print("客户购买的宠物消息:\n" + this.animalsList.get(0).toString());
            this.balance += this.animalsList.get(0).price;
            this.profit += this.animalsList.get(0).price * profitRate;
            animalsList.remove(0);
        }
        System.out.println("当前客户：" + customer.toString());
    }

    @Override
    public void closeShop() {
        this.setClosed(true);
        System.out.println("已经关店，今日客户：");
        for (int i = 0; i < customersList.size(); i++) {
            System.out.print("第" + (i + 1) + "个客户:" + customersList.get(i).toString());
        }
        System.out.println("今日利润：" + this.profit);
    }

}
