package work2;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double money;
    private String name;
    private boolean isOpen;
    // 添加了修饰符
    private ArrayList<Animals> animal = new ArrayList<>();
    // 添加了修饰符
    private ArrayList<Customer> customer = new ArrayList<>();

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public MyAnimalShop(String name, double money, boolean isOpen) {
        this.money = money;
        this.isOpen = isOpen;
        this.name = name;
    }


    public ArrayList<Animals> getAnimals() {
        return animal;
    }

    public void setAnimals(ArrayList<Animals> animals) {
        this.animal = animals;
    }

    public ArrayList<Customer> getCustomers() {
        return customer;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customer = customers;
    }


    @Override
    public void buying(Animals animals) {
        try {
            if (money < animals.getPrice()) {
                throw new InsufficientBalanceException();
            } else {
                animal.add(animals);
                money = money - animals.getPrice();
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(this.name + e);
        }
    }

    @Override
    public void entertainCustomer(Customer customers, int i) {
        // 解决了下表越界问题
        if (i < 1||i > animal.size()) {
            System.out.println("无效购买");
        }else {
            customer.add(customers);
            // 此处增加了用户更新状态
            customers.setVisitNums(customers.getVisitNums() + 1);
            try {
                if (isOpen) {
                    if (animal.size() == 0) {
                        throw new AnimalNotFoundException(customers);
                    } else {

                        System.out.println(
                                customers.getName() +
                                        ",你要买的是:" + animal.get(i - 1).getName()
                                        + ",性别是:" + animal.get(i - 1).getGender()
                                        + ",年龄是:" + animal.get(i - 1).getAge()
                                        + ",价格是:" + animal.get(i - 1).getPrice()
                        );
                        money = money + animal.get(i - 1).getPrice();
                        animal.remove(i - 1);
                    }
                } else {
                    System.out.println("不好意思" + customers.getName() + "已经关门了");
                }
            } catch (AnimalNotFoundException e) {
                System.out.println(e);
            }
        }

    }

    @Override
    public void close() {
        Iterator<Customer> iterator = customer.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("今日剩余" + money);
        setOpen(false);
    }
}
