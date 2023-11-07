import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop {
    private double balance;
    private List<Animal> animalList;
    private List<Customer> customerList;
    private boolean isOperating;
    private double profit;

    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
        this.isOperating = true;
    } public List<Customer> getCustomerList() {
        return customerList;
    }
    /**
     * 购买动物（每购买一条宠物就输出该宠物信息，钱不够就抛出InsufficientBalanceException）
     * @param animal          动物
     * @throws InsufficientBalanceException   余额不足
     */
    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException {
        try {
            if (balance >= animal.price) {
                animalList.add(animal);
                balance -= animal.price;
                if(animal.price==100) {
                    System.out.println("买入的宠物为狗：" + animal.name + "," + animal.age + "." + animal.gender);
                }else {
                    System.out.println("买入的宠物为猫：" + animal.name + "," + animal.age + "." + animal.gender);
                }
            } else {
                throw new InsufficientBalanceException("没钱！！！");
            }
        } catch (InsufficientBalanceException e) {
            System.out.println(e);
        }
    }
    /**
     * 服务顾客（顾客每买一条宠物就输出该宠物信息并将其从列表中移除，如果该顾客第一次光临就将其添加至顾客列表否则该顾客的visitCount+1并且更新LocalDate，如果宠物列表为空则抛出AnimalNotFoundException）
     * @param customer     顾客
     * @param index        顾客购买的是那只宠物
     * @throws AnimalNotFoundException       没有动物了
     */
    @Override
    public void serveCustomer(Customer customer, int index) throws AnimalNotFoundException {
        try {
            // 判断关门没
            if (!isOperating) {
                System.out.println("关门了");
                return;
            }
            // 判断还有没有宠物
            if (animalList.isEmpty()) {
                throw new AnimalNotFoundException("没有宠物可以卖了！！！");
            }
            Animal animal = animalList.remove(index);
            System.out.println("出售的宠物" + animal.toString());
            profit += animal.price;
            // 如果列表已包含该顾客则只更新该顾客信息，否则还要将该顾客添加至顾客列表
            if (customerList.contains(customer)) {
                updateCustomerVisit(customer);
            } else {
                customerList.add(customer);
                updateCustomerVisit(customer);
            }
            balance += animal.price;
        } catch (AnimalNotFoundException e) {
            System.out.println(e);
        }
    }
    /**
     * 这是更新顾客信息的方法
     * @param customer     顾客
     */
    private void updateCustomerVisit(Customer customer) {
        customer.setVisitCount(customer.getVisitCount() + 1);
        customer.setLatestVisitDate(LocalDate.now());
    }
    /**
     * 这是歇业方法，包括计算利润，关门，以及输出顾客列表
     */
    @Override
    public void closeShop() {
        isOperating = false;
        for (Customer customer : customerList) {
            System.out.println(customer.toString());
        }
        System.out.println("今天卖宠物赚了多少：" + profit);
    }
}
