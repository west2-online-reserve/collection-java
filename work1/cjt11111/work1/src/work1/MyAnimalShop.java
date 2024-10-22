package work1;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop{
        private double balance;//店的余额
        public ArrayList<Animal> list =new ArrayList<>();//存放动物的表
        public ArrayList<Customer> customerArrayList=new ArrayList<>();//顾客列表留作纪念的表
        private boolean isOpen;//判断是否营业
        public static final int OPEN_TIME = 10;
        public static final int CLOSE_TIME = 22;
        Scanner scanner=new Scanner(System.in);


        public double getBalance() {
                return balance;
        }

        public void setBalance(double balance) {
                this.balance = balance;
        }

        public ArrayList<Animal> getList() {
                return list;
        }

        public void setList(ArrayList<Animal> list) {
                this.list = list;
        }

        public ArrayList<Customer> getCustomerArrayList() {
                return customerArrayList;
        }

        public void setCustomerArrayList(ArrayList<Customer> customerArrayList) {
                this.customerArrayList = customerArrayList;
        }

        public boolean isOpen() {
                return isOpen;
        }

        public void setOpen(boolean open) {
                isOpen = open;
        }

        @Override
        public String toString() {
                return "MyAnimalShop{" +
                        "balance=" + balance +
                        ", list=" + list +
                        ", customerArrayList=" + customerArrayList +
                        ", isOpen=" + isOpen +
                        ", t=" + t +
                        '}';
        }

        @Override
        public void BuyAnimal(Animal animal) {
                try {
                        if(balance>=animal.getPrice()){
                                balance-=animal.getPrice();
                                list.add(animal);
                                System.out.println("购买"+animal.getName()+"成功");
                                System.out.println("余额为："+balance);
                        }else{
                                throw  new InsufficientBalanceException("余额不足~~~");
                        }
                } catch (InsufficientBalanceException e) {
                        e.printStackTrace();
                }

        }

        @Override
        public void EntertainingCustomer(Customer customer) {


              if(isOpen()){

                        for (int i = 0; i < customerArrayList.size(); i++) {
                                if(customer.getCustomername().equals(customerArrayList.get(i).getCustomername())){
                                        System.out.println("欢迎再次光临！");
                                }
                        }
                        if(!customerArrayList.contains(customer)) {
                                customerArrayList.add(customer);
                                System.out.println("欢迎光临！");
                        }
                        customer.setCount(customer.getCount()+1);//到店次数+1
                        customer.setTime(LocalTime.now());//最新到店时间:

                        System.out.println("店内的宠物如下：");
                        for (int i = 0; i < list.size(); i++) {
                                System.out.println(list.get(i));
                        }
                        System.out.println("请输入宠物种类：");
                        String a=scanner.next();
                        int i;
                        int flag=0;
                        for (i = 0; i < list.size(); i++) {
                              flag=0;
                                if(a.equals(list.get(i).getName())){
                                        System.out.println("购买成功~~~");
                                        balance+=list.get(i).getPrice();
                                        System.out.println("卖出"+list.get(i).toString());
                                        list.remove(i);
                                        flag=1;
                                        break;
                                }
                        }
                        try {
                                if(flag==0){
                                        throw new AnimalNotFountException("店内没有该宠物哦~~~");
                                }
                        } catch (AnimalNotFountException e) {
                                e.printStackTrace();
                        }
                }
                else
                {
                        System.out.println("店铺已经休息啦~~~");
                }
        }
   public LocalTime t= LocalTime.now();
        @Override
        public void Close(boolean c) {
                if(t.getHour()<OPEN_TIME||t.getHour()>CLOSE_TIME||!c) {
                        System.out.println("店铺已经休息啦~~~");
                        for (int i = 0; i < customerArrayList.size(); i++) {
                                System.out.println("店内信息为：");
                                System.out.println(toString());
                        }

                }
                else
                {
                        setOpen(true);
                }
                System.out.println("今日余额："+balance);
        }
}
