package Eyrine;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    Scanner scanner=new Scanner(System.in);
    private double initialBalance=500;
    private double balance;
    private boolean isOpen;
    List<Animal> list=new ArrayList<>();
    ArrayList<Customer> arraylist=new ArrayList<>();
    public MyAnimalShop(double balance, boolean isOpen) {

        this.balance = balance;
        this.isOpen = isOpen;
    }
    public List<Animal> getList() {
        return list;
    }
    @Override
    public void buyAnimal(Animal animal) {
        if(balance < animal.getPrice())
                throw new InsufficientBalanceException("余额不足，购买失败");
        else{
            balance-= animal.getPrice();
            list.add(animal);
            System.out.println("购入小动物 "+animal.getName()+"，购入价格为："+animal.getPrice());
        }

    }

    @Override
    public void treat(Customer customer) {
        if(customer.getNow().equals(LocalDate.now())){
            customer.num++;
            arraylist.add(customer);
            System.out.println("欢迎光临："+customer.getName());
            if(list.isEmpty()){
                System.out.println("本店宠物均已出售，欢迎下次光临");
            }else{
                int index=0;

                System.out.println("请输入想要购买的宠物的编号");
                index=scanner.nextInt();
                if(index<=list.size()-1){
                    Animal animal=list.get(index);
                    balance += animal.getPrice();
                    list.remove(animal);
                    toString(animal);
                }else{
                    throw new AnimalNotFoundException("宠物店内没有该宠物");
                }
            }
        }else{
            isOpen=false;
            System.out.println("本店已关，欢迎下次光临");
        }
        isClosed();
    }

    @Override
    public void isClosed() {

        if(!(isOpen)) {
            System.out.println("今日顾客总列表:" + arraylist);
            if (balance - initialBalance >= 0) {
                System.out.println("今日总盈利" + (balance - initialBalance));
            } else {
                System.out.println("今日总亏损" + (initialBalance - balance));
            }
        }
    }



    public static void toString(Animal animal) {
        System.out.println("出售动物："+animal.getName()+"，年龄"+ animal.getAge()+"，性别"+ animal.getGender()+"，出售价格"+animal.getPrice());
    }

}
