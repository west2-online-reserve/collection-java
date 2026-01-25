package Instance;
import Instance.Customers.Customer;
import Interface.AnimalShop;

import java.time.LocalDate;
import java.util.ArrayList;

class InsufficientBalanceException extends RuntimeException{
    public InsufficientBalanceException(String message){
        super(message);
    }
}
class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message){
        super(message);
    }
}
public class MyAnimalShop implements AnimalShop {
    double remainMoney;
    ArrayList<Animal> animals;
    ArrayList<Customer> customers;
    boolean isOpen;

    public MyAnimalShop(double remainMoney, ArrayList<Animal> animals, ArrayList<Customer> customers, boolean isOpen) {
        this.remainMoney = remainMoney;
        this.animals = animals;
        this.customers = customers;
        this.isOpen = isOpen;
    }
    @Override
    public void buyAnimal(Animal animal, int number) throws InsufficientBalanceException {
        double TotalMoney=number*animal.price;
        if(TotalMoney>remainMoney){
            throw new InsufficientBalanceException("购买失败,余额不足");
        }else{
            animals.add(animal);
            remainMoney -= animal.price;
            System.out.println("买入"+animal.name+"成功，剩余余额"+remainMoney);
        }
    }

    @Override
    public void welcomeCustomer(Customer customer,Animal animal) throws AnimalNotFoundException {
        if(!isOpen){
            System.out.println("当前店铺未营业");
        }
        if(animals.isEmpty()){
            throw new AnimalNotFoundException("售出失败,店内动物已经卖完");
        }
        System.out.println("欢迎光临");
//        统计购买信息
        animals.remove(animal);
        System.out.println("卖出动物信息："+animal.toString());
        remainMoney += animal.price;
//        添加客户信息
        customer.visitNumber+=1;
        customer.setFinalVisitDate(LocalDate.now());
        customer.spend+=animal.price;
        customers.add(customer);
    }

    @Override
    public void closeShop() {
        System.out.println("关闭店铺");
        isOpen=false;
        LocalDate today = LocalDate.now();
        double TotalMoney = 0;
//        统计当天客户
        System.out.println("统计当天客户");
        for(Customer customer: customers){
            if(customer.getFinalVisitDate().isEqual(today)){
                System.out.println(customer.toString());
                TotalMoney+=customer.spend;
            }
        }
        System.out.println("今日进账"+TotalMoney);

    }
}

