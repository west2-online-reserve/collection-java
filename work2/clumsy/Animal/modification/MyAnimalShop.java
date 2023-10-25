package modification;
/*
* @Author:clumsy
* @Description: 我的宠物商店
* @Date： 20023/10/18
* */

import javax.naming.InsufficientResourcesException;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class MyAnimalShop implements AnimalShopinterface {
    private double Shopbalance;
    private double balance;//初始费用
    private ArrayList<Animal> AnimalList=new ArrayList<Animal>();
    private LinkedList<Customer> CustomerList=new LinkedList<Customer>();
    private boolean isClosed;
    private LocalDate time;
    private static DateFormat df=DateFormat.getDateTimeInstance();//DateFormat 用来输出标准时间。
    private final double profit=0;


    public MyAnimalShop(double balance,boolean isClosed) {
        this.balance = balance;
        this.isClosed=false;
        this.time=LocalDate.now();
        Shopbalance=balance;
    }

    public MyAnimalShop() {}

    public ArrayList<Animal> getAnimalList() {
        return AnimalList;
    }

    public void setAnimalList(ArrayList<Animal> animalList) {
        AnimalList = animalList;
    }

    public LinkedList<Customer> getCustomerList() {
        return CustomerList;
    }

    public void setCustomerList(LinkedList<Customer> customerList) {
        CustomerList = customerList;
    }

    public double getProfit() {
        return profit;
    }

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
                System.out.println("购买成功，买进"+num+"只"+d.toString("mi"));//toString重载 方法是输出买进动物信息，买进动物进价给店长看。
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
                System.out.println("用户到店时间是"+df.format(new Date()));
                //顾客到店时间
                System.out.println("欢迎"+s.getName()+"光临本宠物店");//欢迎顾客
                CustomerList.add(s);
                if (AnimalList.isEmpty()) {//是否还有宠物
                    throw new AnimalNotFountException("抱歉，本店宠物已经售空");
                }
                else {
                    if (!AnimalList.contains(pet)){
                        System.out.println("抱歉，本店此宠物已经售空，您可挑选其他宠物，其他宠物如下：");
                        System.out.println(AnimalList);
                    }
                    else{
                        System.out.println("老板好眼力，您挑选的宠物可是上品，下面是相关信息："+"\n"+pet.toString());
                        System.out.println("顾客请您支付"+pet.price*2);
                        Shopbalance=Shopbalance+pet.price*2;
                        AnimalList.remove(pet);
                        System.out.println("购买成功，宠物信息如下："+"\n"+pet.toString());//toString 方法是输出售卖价格给用户看。
                        s.setNums(s.getNums()+1);
                        //增加顾客到店次数，在顾客信息中，isclose中输出
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
            if (!AnimalList.isEmpty()){
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
