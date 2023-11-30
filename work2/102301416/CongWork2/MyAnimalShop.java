package CongWork2;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
//102301416姚敏聪
public class MyAnimalShop implements AnimalsShop{
    static final int OPEN_TIME = 9;//开店时间
    static final int CLOSE_TIME =18 ;//关店时间，如要进行Test,可以修改次时间进行运行
    private double account=2000;//宠物店初始金额
    private double balance=0;//收支
    ArrayList<Animals> listAnimals=new ArrayList<>();
    ArrayList<Customer2> listCustomer=new ArrayList<>();
    boolean isOpen;//判断是否营业
    Scanner scanner=new Scanner(System.in);

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void buyAnimals(Animals animals) {
        if (animals.getPrice()>account){
            throw new InsufficientBalanceException("宠物店余额不足");
        }else {
            listAnimals.add(animals);
            this.balance-=animals.getCost();
            this.account-=animals.getCost();
            System.out.println("进货成功");
        }
    }


    @Override
    public void careCustomer(Customer2 customer2) {
        if (isOpen){
            //判断顾客是否是同一个人
            if (listCustomer.contains(customer2)){
                System.out.println("感谢您再次光临");
            }else {
                listCustomer.add(customer2);
                System.out.println("欢迎光临");
            }
            customer2.setCount(customer2.getCount()+1);//到店次数加1
            customer2.setTime(LocalDate.now());
            System.out.println("宠物店宠物如下：");
            for (Object animalsAll:listAnimals){
                System.out.println(animalsAll);
            }
            System.out.println("请输入宠物种类");
            String animalsName=scanner.next();
            int judge=0;//判断有误该宠物
            for(int i=0;i<listAnimals.size();i++){
                if(animalsName.equals(listAnimals.get(i).getName())){
                    System.out.println("购买成功\n");
                    judge=1;
                    account+=listAnimals.get(i).getPrice();
                    balance+=listAnimals.get(i).getPrice();
                    System.out.println("卖出"+listAnimals.get(i).toString());
                    listAnimals.remove(i);
                }
            }
            if(judge==0){
                throw new AnimalNotFountException("宠物店没有该宠物");
            }
        }else{
            System.out.println("宠物店已关闭");
        }
    }

    LocalTime arrivalTime=LocalTime.now();//顾客具体到店时间，判断宠物营业情况

    @Override
    public void close(boolean a) {
        //定义a方便循环进行
        if ((arrivalTime.getHour() >= OPEN_TIME && arrivalTime.getHour() <= CLOSE_TIME )&&a){
            isOpen=true;
        }else {
            System.out.println("宠物店关闭了");
            for (int i=0;i<listCustomer.size();i++){
                System.out.println("今日顾客名单："+listCustomer.get(i).toString());
            }
        System.out.println("今日营利："+this.balance);
        System.out.println("宠物店余额："+this.account);
        }
    }
}
