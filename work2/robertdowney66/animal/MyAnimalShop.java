package com.west2.check02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/***
 * @author yuyu
 */
public class MyAnimalShop implements AnimalShop {
    /**
     * remainingBalance：店内余额
     */
    protected double remainingBalance;
    protected ArrayList<AbstractAnimal> animalList = new ArrayList<>();
    protected ArrayList<Customer> customersList = new ArrayList<>();
    protected boolean isInOperation;
    /***
     *todeyProfit此步是为了标记今天所售出的金额，使得计算利润步骤简化
     */
    protected double todayProfit;

    /***
     *中间加入instanceOf的判断语句是为了将购入宠物价格更改，不然原价购入原价售出，店铺都倒闭了
     * @param remainingBalance
     * @param animalList
     * @param customersList
     * @param isInOperation
     */
    public MyAnimalShop (double remainingBalance, ArrayList<AbstractAnimal> animalList, ArrayList<Customer> customersList, boolean isInOperation) {
        this.remainingBalance = remainingBalance;
        this.animalList = animalList;
        this.customersList = customersList;
        this.isInOperation = isInOperation;
        for (AbstractAnimal abstractAnimal : animalList) {
            if (abstractAnimal instanceof Cats) {
                abstractAnimal.setPrice(1000);
            } else if (abstractAnimal instanceof ChineseRuralDogs) {
                abstractAnimal.setPrice(500);
            } else if (abstractAnimal instanceof LeopardGeckos) {
                abstractAnimal.setPrice(5000);
            }
        }
    }

    public MyAnimalShop() {
    }

    /***
     * 由于后续需要进行日利润的计算，所以在此处增设购入日期变量purchaseDate
     * 从而实现日利润计算
     * 中间加入instanceOf的判断同样也是为了将购入宠物价格更改，不然原价购入原价售出，店铺都倒闭了
     * @param animal
     */
    @Override
    public void purchaseOfNewAnimal(AbstractAnimal animal, LocalDate purchaseDate) throws InsufficieBalanceException {
       if (isInOperation){
           if (remainingBalance >= animal.getPrice()) {
               animalList.add(animal);
               remainingBalance -= animal.getPrice();
               if (purchaseDate.equals(LocalDate.now())) {
                   todayProfit -= animal.getPrice();
               }
               if (animal instanceof Cats) {
                   animal.setPrice(1000);
               } else if (animal instanceof ChineseRuralDogs) {
                   animal.setPrice(500);
               } else if (animal instanceof LeopardGeckos) {
                   animal.setPrice(5000);
               }
               System.out.println("购买成功");
               System.out.println("当前店内宠物为：" );
               for (AbstractAnimal abstractAnimal : animalList) {
                   System.out.println(abstractAnimal);
               }
               System.out.println();
           } else {
               throw new InsufficieBalanceException("余额不足");
           }
       }else {
           System.out.println("店铺未开张");
           System.out.println();
       }

    }

    @Override
    public void treatCustomers (Customer customer, AbstractAnimal desiredAnimal) {
       if (isInOperation){
           if (!customersList.contains(customer)) {
               customersList.add(customer);

           }
           customer.setArrvalTimes(customer.getArrvalTimes()+1);
           if (animalList.contains(desiredAnimal)) {
               animalList.remove(desiredAnimal);
               System.out.println("动物售出成功,动物信息为：");
               System.out.println(desiredAnimal);
               System.out.println();
               remainingBalance += (desiredAnimal).getPrice();
               if (customer.getLastestArrvalTime().equals(LocalDate.now())) {
                   todayProfit += desiredAnimal.getPrice();
               }
           } else {
               throw new AnimalNotFoundException("未找到该动物");
           }
       }else {
           System.out.println("店铺未开张");
           System.out.println();
       }

    }

    @Override
    public void closureOfBusiness() {
        Iterator iterator = customersList.iterator();
        LocalDate localDate = LocalDate.now();
        System.out.println("当天光顾的顾客有：");
        while (iterator.hasNext()) {
            Customer customer = (Customer) iterator.next();
            if (customer.lastestArrvalTime.equals(localDate)) {
                System.out.println(customer);
            }
        }
        System.out.print("今日的利润是：");
        System.out.println(todayProfit);
        isInOperation = false;
        System.out.println();
    }
}
