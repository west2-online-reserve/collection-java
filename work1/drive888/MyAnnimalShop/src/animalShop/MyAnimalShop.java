package animalShop;

import animal.Animal;
import customer.Customer;
import exception.AnimalNotFoundException;
import exception.InsufficientBalanceException;
import exception.OpenException;

import java.time.LocalDate;
import java.util.List;

public class MyAnimalShop implements AnimalShop {

    private double money;
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOpen = false;

    private double todayMoney = 0;

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getTodayMoney() {
        return todayMoney;
    }

    public void setTodayMoney(double todayMoney) {
        this.todayMoney = todayMoney;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * 进货：买入一只新动物
     *
     * @param animal 买入的动物
     * @return 是否买入成功
     */
    @Override
    public void buyNewAnimal(Animal animal) {
        if (getMoney() < animal.getPrice()) {
            throw new InsufficientBalanceException();
        }
        this.setMoney(getMoney() - animal.getPrice());
        animalList.add(animal);
    }

    @Override
    public String welcome(Customer customer) {
        if (!this.isOpen()) {
            throw new OpenException("今天还没开业");
        }
        return "欢迎光临，" + customer.getCustomerName();
    }

    @Override
    public String sale(Customer customer, String animalName) {
        if (!this.isOpen()) {
            throw new OpenException("今天还没开业");
        }
        boolean isOldCustomer = false;
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer1 = customerList.get(i);
            if (customer1.getCustomerName().equals(customer.getCustomerName())) {
                // 这个人是老客户
                if (!customer1.getLastComeDate().isBefore(LocalDate.now())) {
                    customer1.setLastComeDate(LocalDate.now());
                    customer1.setComeCount(customer1.getComeCount() + 1);
                }
                isOldCustomer = true;
                break;
            }
        }
        if (!isOldCustomer) {
            customer.setLastComeDate(LocalDate.now());
            customer.setComeCount(customer.getComeCount() + 1);
            customerList.add(customer);
        }
        if (animalList.isEmpty()) {
            throw new AnimalNotFoundException();

        }
        //店里是否有顾客需要的动物
        boolean hasBuyAnimal = false;
        for (int i = 0; i < animalList.size(); i++) {
            Animal animal = animalList.get(i);
            if (animal.getName().equals(animalName)) {
                //将顾客购买的动物从动物清单中去出除
                boolean remove = animalList.remove(animal);
                System.out.println("顾客购买的宠物：" + animal);
                hasBuyAnimal = true;
                //将钱收入帐
                this.setMoney(this.getMoney() + animal.getPrice());
                this.setTodayMoney(this.getTodayMoney() + animal.getPrice());
                break;

            }

        }
        if (!hasBuyAnimal) {
            throw new AnimalNotFoundException("没有顾客要购买的动物");
        }
        return "感谢购买 ";


    }

    @Override
    public void open() {
        //需手改宠物店的开店情况
        setOpen(true);
        setTodayMoney(0);
    }

    @Override
    public void stop() {
        setOpen(false);
        LocalDate now = LocalDate.now();
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            if (customer.getLastComeDate().isEqual(now)) {
                System.out.println(customer);
            }

        }
        System.out.println("今天的营业额" + todayMoney);
    }


}

