import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    List<Animal> animalList = new LinkedList<>();
    List<Customer> customerList = new LinkedList<>();
    private double blance;
    private boolean isOpen;
    private double profit = 0;

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        animalList = animalList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        customerList = customerList;
    }

    public double getBlance() {
        return blance;
    }

    public void setBlance(double blance) {
        this.blance = blance;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public MyAnimalShop(double blance, boolean isOpen) {
        this.blance = blance;
        this.isOpen = isOpen;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        if (isOpen == false) {
            System.out.println("抱歉，本店已经关门了");
            return;
        } else {
            try {
                if (blance > animal.getPrice()) {
                    blance -= animal.getPrice();
                    animalList.add(animal);
                    System.out.println("目前总共有" + animalList.size() + "只动物");
                    System.out.println("成功购买一只" + animal);
                } else {
                    System.out.println("余额不足");
                }
            } catch (InsufficientBalanceException e) {
                System.out.println(e.toString());
            }
        }
    }

    @Override
    public void Customer(Customer customer) throws AnimalNotFoundException {
        System.out.println("=========================");
        try {
            if (isOpen == false) {
                System.out.println("宠物店已打烊.\n");
                return;
            }
            if (animalList.size() != 0) {
                customerList.add(customer);
                System.out.println(customer.getName() + "先生,以下是你购买的宠物信息:");
                int number = (int) (Math.random() * animalList.size());
                Animal animal = animalList.get(number);
                animalList.remove(number);
                if (animal instanceof Cat) {
                    profit += 200;
                } else if (animal instanceof ChineseRuralDog) {
                    profit += 100;
                } else if (animal instanceof Tiger) {
                    profit += 500;
                }
                System.out.println(animal);
                customer.setTimes(customer.getTimes() + 1);
                customer.setLatestArrivedTime(LocalDate.now());
            } else {
                throw new AnimalNotFoundException("抱歉，当前没有可以宠物可以购买");
            }
        } catch (AnimalNotFoundException e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public void closedoor() {
        System.out.println("今日光顾的顾客有：");
        for (Customer it : customerList) {
            System.out.println(it.toString());
        }
        System.out.print("今日的利润为：");
        System.out.println(getProfit());
    }
}
