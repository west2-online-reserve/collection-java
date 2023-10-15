package Animal;

import Animal.AnimalNotFountException;
import Animal.AnimalShopinterface;
import RunTimeException.Customer;
import Animal.InsufficientBalanceException;

import javax.naming.InsufficientResourcesException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

public class MyAnimalShop implements AnimalShopinterface {
    private double Shopbalance;
    private double balance;//初始费用
    ArrayList<Animal> AnimalList=new ArrayList<Animal>();
    LinkedList<Customer> CustomerList=new LinkedList<Customer>();
    Boolean isClosed;
    public LocalDate time;
    private double profit=0;


    public MyAnimalShop(double balance,boolean isClosed) {
        this.balance = balance;
        this.isClosed=false;
        this.time=LocalDate.now();
        Shopbalance=balance;
    }

    public MyAnimalShop() {}

    @Override
    public void purchase(Animal d, int num) throws InsufficientBalanceException {
        if (isClosed){
            System.out.println("抱歉，本店已经打样");
        }
        else{
            double totalMenoy=d.getPrice()*num;
            if (Shopbalance>=totalMenoy){//买进的宠物添加在列表里
                Shopbalance=Shopbalance-totalMenoy;//扣除费用
                for (int i=1;i<=num;i++){//添加买进的动物
                AnimalList.add(d);
                }
                System.out.println("购买成功，买进"+num+"只"+d.toString("mi"));//买进动物信息输出
                System.out.println("余额是"+Shopbalance);
                System.out.println("_______________");
            }
            else {
                try {
                    throw new InsufficientResourcesException("购进失败，余额不足");
                } catch (InsufficientResourcesException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    @Override
    public void treat(Customer s, Animal pet) {
        try {
            if (isClosed){//是否开门
                System.out.println("抱歉，本店已经打样");
            }
            else {
                System.out.println("欢迎"+s.getName()+"光临本宠物店");//欢迎顾客
                CustomerList.add(s);
                if (AnimalList.isEmpty()) {//是否还有宠物
                    throw new AnimalNotFountException("抱歉，本店宠物已经售空");
                }
                else {
                    if (!AnimalList.contains(pet)){
                        System.out.println("抱歉，本店此宠物已经售空，您可挑选其他宠物，其他宠物如下：");
                        System.out.println(AnimalList);
                        return;
                    }
                    else{
                        System.out.println("老板好眼力，您挑选的宠物可是上品，下面是相关信息："+"\n"+pet.toString());
                        System.out.println("顾客请您支付"+pet.price*2);
                        Shopbalance=Shopbalance+pet.price*2;
                        AnimalList.remove(pet);
                        System.out.println("购买成功，宠物信息如下："+"\n"+pet.toString());
                        s.setNums(s.getNums()+1);
                        }
                    }
                }
            }catch (AnimalNotFountException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void isClosed() {
        if (!isClosed){
            isClosed=true;
            System.out.println("今日客户量为"+CustomerList.size());
            System.out.println("客户信息是"+CustomerList.toString());
            double profit=Shopbalance-balance;
            System.out.println("今日利润是"+profit);
            System.out.println("账户余额是"+Shopbalance);
            System.out.println("今日还剩余"+AnimalList.size()+"只动物。");
            if (AnimalList.size()!=0){
                System.out.println("还剩下这些宠物"+AnimalList);
            }
            else{
                System.out.println("今日将宠物全部售出。");
            }
        }
        else {
            System.out.println("抱歉，本宠物店已经打样。");
        }
    }

    @Override
    public void isOpen() {
        if (isClosed){
            isClosed=false;
            System.out.println("今天是"+time);
            System.out.println("本店开张，欢迎顾客前来挑选心仪宠物");
            System.out.println("本店宠物有如下："+AnimalList);
        }
    }

}
