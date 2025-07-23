package MyPetShop;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Objects;

public class MyAnimalShop implements AnimalShop{

    double balance;
    double profit = 0;
    ArrayList<Animal> listAnimal = new ArrayList<>();
    ArrayList<Customer> listCustomer = new ArrayList<>();
    boolean isOpen;

    MyAnimalShop() {}

    void openShop(double balance) {
        this.balance = balance;
    }

    //买入新动物
    @Override
    public void buyNewAnimal(Animal animal){
        if(balance - animal.purchasePriceAnimal < 0) throw new InsufficientBalanceException("您的余额不足");
        balance -= animal.purchasePriceAnimal;
        listAnimal.add(animal);
        System.out.println("购买成功:\n"+animal.getAnimalInformation());
        System.out.println("店内余额:"+balance+"\n");
    }

    //初始化顾客信息 亲爱的老顾客
    public void initialCustomerInformation(Customer customer) {
        listCustomer.add(customer);
    }

    //更新顾客信息
    public void updateCustomerMessage(Customer customer) {
        if (!listCustomer.contains(customer)) listCustomer.add(customer); //欢迎光临~
        customer.updateFrequency();
        customer.updateLatestTime();
    }

    //小店赚钱了
    public void getIncome(Animal animal) {
        balance += animal.sellPriceAnimal;
        profit += animal.sellPriceAnimal-animal.purchasePriceAnimal;
    }

    //更新动物信息
    public void updateAnimalMessage(Animal animal, Customer customer) {
        animal.getMaster(customer.getNameCustomer());
        System.out.println(animal.toString());
        listAnimal.remove(animal);
    }

    //招待顾客
    @Override
    public void entertainCustomer(Customer customer, Animal animal){
        System.out.println("欢迎光↑临↓~"+customer.getNameCustomer()+"女士");
        updateCustomerMessage(customer);
        if (!listAnimal.isEmpty()) {
            if (listAnimal.contains(animal)) {
                System.out.println("恭喜您购买成功");
                getIncome(animal);
                updateAnimalMessage(animal, customer);
            }else throw new AnimalNotFoundException();
        }else throw new AnimalNotFoundException();
    }

    //歇业
    @Override
    public void closeShop(){
        isOpen = false;
        for (Customer customer : listCustomer) {
            if (Objects.equals(customer.getLatestTime(), LocalDate.now())) System.out.println(customer.toString()+"\n");
        }
        System.out.println("今日份收益:"+profit);
    }

}
