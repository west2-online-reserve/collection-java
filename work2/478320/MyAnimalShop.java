package com.huayu.work02;

/**
 * MyAnimal是AnimalShop的实现类，代表宠物店运行的核心
 *
 * 该类包含具体的购买宠物，招待顾客，歇业的实现方法
 * @author yusiheng
 * @date 2023/10/04
 */
import java.time.LocalDate;
import java.util.ArrayList;


public class MyAnimalShop implements AnimalShop {

    private double storeBalance;
    private ArrayList<Animal> animalsList = new ArrayList<>();
    private ArrayList<Customer> customersList = new ArrayList<>();
    private ArrayList<Double> soldOnClosedDays = new ArrayList<>();
    private ArrayList<Double> importOnClosedDays = new ArrayList<>();
    private LocalDate closingTime;
    private boolean businessInProgress;

    public double getStoreBalance() {
        return storeBalance;
    }

    public void setStoreBalance(double storeBalance) {
        this.storeBalance = storeBalance;
    }

    public ArrayList<Animal> getAnimalsList() {
        return animalsList;
    }

    public void setAnimalsList(ArrayList<Animal> animalsList) {
        this.animalsList = animalsList;
    }

    public ArrayList<Customer> getCustomersList() {
        return customersList;
    }

    public void setCustomersList(ArrayList<Customer> customersList) {
        this.customersList = customersList;
    }

    public ArrayList<Double> getSoldOnClosedDays() {
        return soldOnClosedDays;
    }

    public void setSoldOnClosedDays(ArrayList<Double> soldOnClosedDays) {
        this.soldOnClosedDays = soldOnClosedDays;
    }

    public ArrayList<Double> getImportOnClosedDays() {
        return importOnClosedDays;
    }

    public void setImportOnClosedDays(ArrayList<Double> importOnClosedDays) {
        this.importOnClosedDays = importOnClosedDays;
    }

    public LocalDate getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDate closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isBusinessInProgress() {
        return businessInProgress;
    }

    public void setBusinessInProgress(boolean businessInProgress) {
        this.businessInProgress = businessInProgress;
    }

    /**
     * 这是购买动物方法，可以购买动物，购买后会把该动物加入我的动物列表，并消耗一部分我原有的钱，如果购买的时间是歇业计算利润的时间，这个动物的购买价格就会进入利润计算用的进口列表，如果已经没钱就会抛出钱不够的异常并捕获，输出异常信息
     *
     * @param animal 动物
     * @param importTime 进口时间
     * @return null
     * @throws InsufficientBalanceException 已有的钱不足以支付该宠物的进价
     */
    @Override
    public void purchaseNewAnimal(Animal animal, LocalDate importTime) {
        if (storeBalance < animal.getAnimalImportPrice()) {
            try {
                throw new InsufficientBalanceException();
            } catch (InsufficientBalanceException e) {
                System.out.println(e);
            }
        }
        // 计算利润
        storeBalance = storeBalance - animal.getAnimalImportPrice();
        // 动物列表添加动物
        animalsList.add(animal);
        // 如果购买宠物时间和当天时间相同，花销列表增这个宠物的进口价
        if (importTime.equals(closingTime)) {
            importOnClosedDays.add(animal.getAnimalImportPrice());
        }
    }

    /**
     *这是招待顾客方法，可以出售宠物，并把当天光顾的顾客加入顾客列表，赚取一定的钱，如果购买的时间是歇业计算利润的时间，这个动物的出售价格就会进入利润计算用的进口列表，如果已经没有宠物可购买就会抛出钱不够的异常并捕获，输出异常信息
     *
     * @param customer 购买动物的顾客
     * @param whichOneToPurchase 购买哪一只动物
     * @param animal 动物
     * @throws IndexOutOfBoundsException 店内已经没有宠物
     */
    @Override
    public void hospitalizingCustomers(Customer customer, int whichOneToPurchase, Animal animal) throws IndexOutOfBoundsException {
        if (animalsList.size() == 0) {
            try {
                throw new AnimalNotFountException();
            } catch (AnimalNotFountException e) {
                System.out.println(e);
            }
        }
        if (animalsList.size() <= 0) {
            throw new IndexOutOfBoundsException();
        }
        if (animalsList.size() > 0) {
            // 输出顾客购买的宠物信息
            System.out.println(animal.toString());
            storeBalance = storeBalance + animal.getAnimalPrice()
            // 从动物列表移除动物
            animalsList.remove(whichOneToPurchase - 1);
        }
        // 顾客列表增加顾客
        customersList.add(customer);
        // 如果购买顾客光临时间和当天时间相同，售出列表增这个宠物的出口价
        if (customer.getLatestArrivalTime().equals(closingTime) && animalsList.size() > 0) {
            soldOnClosedDays.add(animal.getAnimalPrice());
        }
    }

    /**
     * 这是歇业方法，计算当天利润，同时还有输出当天关顾顾客的功能
     *
     * @param customer 顾客
     * @param revenue 接收花销列表中所有花销的总数
     * @param expenditure 接收售出列表所有盈利的总数
     */
    @Override
    public void closureOfBusiness(Customer... customer) {
        for (int i = 0; i < customer.length; i++) {
            if (customer[i].getLatestArrivalTime().equals(closingTime)) {
                System.out.println(customer[i].toString());
            }

        }
        double revenue = 0;
        // 计算当天总花销
        for (int j = 0; j < importOnClosedDays.size(); j++) {
            revenue = revenue + importOnClosedDays.get(j);
        }
        double expenditure = 0;
        // 计算当天总盈利
        for (int j = 0; j < soldOnClosedDays.size(); j++) {
            expenditure = expenditure + soldOnClosedDays.get(j);

        }
        // 计算利润
        double profit = expenditure - revenue;
        System.out.println(profit);

    }
}
