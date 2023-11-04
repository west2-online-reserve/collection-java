package work2;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double money;
    private String name;
    private boolean isOpen;
    ArrayList<Animals> animal = new ArrayList<>();
    ArrayList<Customer> customer = new ArrayList<>();

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
        customer.add(customers);
        try {
            if (isOpen) {
                if (animal.size() == 0) {
                    throw new AnimalNotFoundException(customers);
                } else {

                    System.out.println(
                            animal
//                            customers.getName()+
//                            ",你要买的是:"+animal.get(i-1).name
//                            +",性别是:"+animal.get(i-1).gender
//                            +",年龄是:"+animal.get(i-1).age
//                            +",价格是:"+animal.get(i-1).price
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
