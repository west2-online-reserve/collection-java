package bean;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAnimalShop implements AnimalShop {
    Scanner sc = new Scanner(System.in);
    private double balance;//店内余额
    private ArrayList<Animal> petList = new ArrayList<>();//一个存放动物的列表
    private ArrayList<Customer> customerList = new ArrayList<>();//一个顾客列表
    private boolean isOpen;//是否正在营业
    private double catPrice;//猫猫出售价格
    private double chineseRuralPrice;//中华田园犬出售价格


    @Override
    public void buyPet(Animal pet) {//宠物店买入动物的方法
        try {
            if (pet instanceof ChineseRuralDog d && balance < pet.getPrice())
                throw new InsufficientBalanceException();
            else if (pet instanceof Cat c && balance < pet.getPrice())
                throw new InsufficientBalanceException();
            else if(pet instanceof ChineseRuralDog d){
                petList.add(pet);//在宠物列表中添加动物
                System.out.println("购买了一只中华田园犬");
                balance -= pet.getPrice();
            }else if(pet instanceof Cat c){
                petList.add(pet);//在宠物列表中添加动物
                System.out.println("购买了一只猫");
                balance -= pet.getPrice();
            }
        } catch (InsufficientBalanceException e) {
            e.showProblem(balance,pet);
        }
    }

    @Override
    public void entertainCustomer(Customer customer) {//招待顾客的方法
        if (customer.getCount() == 0) {
            customer.setCount(customer.getCount()+1);
            customerList.add(customer);//如果顾客没来过，就加到顾客列表中
        } else {
            customer.setCount(customer.getCount()+1);
        }

        for (int i = 0; i < petList.size(); i++) {//输出动物信息
            System.out.print(i + 1 + "号宠物：");
            petList.get(i).toString();
        }

        System.out.println("尊敬的顾客，"+"请输入动物序号来选择你要买的宠物");
        int id = sc.nextInt();
        try {
            if(petList.isEmpty())
                throw new AnimalNotFountException(-1000000);
            else if (id <= 0 || id > petList.size()) {
                throw new AnimalNotFountException(id);//未找到动物,抛出异常
            } else if (petList.get(id-1) instanceof ChineseRuralDog) {
                System.out.println("恭喜"+customer.getName()+"买了一只可爱的中华田园犬");
                customer.setBuyPet(petList.get(id-1));
                System.out.print("这只");petList.get(id-1).toString();
                balance = balance + this.chineseRuralPrice;
                petList.remove(id-1);
            } else if (petList.get(id-1) instanceof Cat c) {
                System.out.println("恭喜" + customer.getName() + "买了一只可爱的猫猫");
                customer.setBuyPet(petList.get(id-1));
                System.out.print("这只");
                petList.get(id - 1).toString();
                petList.get(id - 1);
                balance = balance + this.catPrice;
                petList.remove(id - 1);
            }
        } catch (AnimalNotFountException e) {
            e.showProblem();
        }
    }


    @Override
    public void closeShop() {
        isOpen=false;
        double todayProfit=0;
        System.out.println("本店今日已歇业,以下为本店今日光顾的顾客：");
        for(int i=0;i<customerList.size();i++){
            if(customerList.get(i).getLatestArrivalTime().equals(LocalDate.now())){
                customerList.get(i).toString();
                if(customerList.get(i).getBuyPet() instanceof ChineseRuralDog)
                    todayProfit+=(chineseRuralPrice-new ChineseRuralDog().getPrice());
                else if(customerList.get(i).getBuyPet() instanceof Cat)
                    todayProfit+=(catPrice-new Cat().getPrice());
            }
        }
        System.out.println("当天的利润为："+todayProfit);
    }

    public MyAnimalShop(double balance, ArrayList<Animal> petList1, ArrayList<Customer> customerList1, boolean isOpen, double catPrice, double chineseRuralPrice) {
        this.balance = balance;
        petList = petList1;
        customerList = customerList1;
        this.isOpen = isOpen;
        this.catPrice = catPrice;
        this.chineseRuralPrice = chineseRuralPrice;
    }

    public MyAnimalShop() {
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Animal> getPetList() {
        return petList;
    }

    public void setPetList(ArrayList<Animal> petList1) {
        petList = petList1;
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public double getCatPrice() {
        return catPrice;
    }

    public void setCatPrice(double catPrice) {
        this.catPrice = catPrice;
    }

    public double getChineseRuralPrice() {
        return chineseRuralPrice;
    }

    public void setChineseRuralPrice(double chineseRuralPrice) {
        this.chineseRuralPrice = chineseRuralPrice;
    }
    public void showMyPetShop(){
        for(int i=0;i<petList.size();i++){
            System.out.print(i+1+" ");petList.get(i).toString();
        }
        System.out.println("当前宠物店余额为："+balance);
    }
}
