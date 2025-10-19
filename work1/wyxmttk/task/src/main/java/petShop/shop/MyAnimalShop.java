package petShop.shop;

import petShop.customer.Customer;
import petShop.animal.Animal;
import petShop.exception.AnimalNotFountException;
import petShop.exception.InsufficientBalanceException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MyAnimalShop extends AbstractAnimalShop {
    /**
     * 店的余额double
     * 一个存放动物的列表 (使用ArrayList、LinkedList或其他你喜欢的List实现)
     * 一个顾客列表留作纪念
     * 是否正在营业
     *
     *
     * */

    public MyAnimalShop(double startingBalance) {
        setLastDayBalance(startingBalance);
        setBalance(startingBalance);
        setToday(LocalDate.now());
        setOpen(true);
        System.out.println("启动资金:"+startingBalance);
    }

    @Override
    public void buyAnimal(Animal animal){
        if ((getBalance() - animal.getPrice()) < 0) {
            throw new InsufficientBalanceException("购买" + animal.toString() + "时，余额不足，余额:" + getBalance() + "价格:" + animal.getPrice());
        }
        setBalance(getBalance() - animal.getPrice());
        System.out.println("购买" + animal.toString() + "余额:" + getBalance() + "价格:" + animal.getPrice());

        addAnimal(animal);
    }


    @Override
    public void entertainCustomer(Customer customer, long id) {
        Animal animalToBeBought = getAnimalById(id);
        if (animalToBeBought == null) {
            throw new AnimalNotFountException("没有id为" + id + "的动物");
        }
        setBalance(getBalance() + animalToBeBought.getPrice() + animalToBeBought.getProfit());
        removeAnimalById(id);
        System.out.println("出售" + animalToBeBought + "余额:" + getBalance());
        if (getCustomerById(customer.getId()) == null) {
            addCustomer(customer);
            System.out.println("新客户:" + customer);
        }
        customer.setLastVisitDate(getToday());
        if (getTodayCustomerById(customer.getId()) == null) {
            addTodayCustomer(customer);
        }
    }

    @Override
    public void close() {
        printTodayCustomers();
        clearTodayCustomers();
        setOpen(false);
        System.out.println(getToday()+"打烊 profit:"+computeProfit());
        setLastDayBalance(getBalance());
    }

    @Override
    public void open() {
        setOpen(true);
        setToday(getToday().plusDays(1));
    }

    @Override
    protected double computeProfit() {
        return getBalance()-getLastDayBalance();
    }

}
