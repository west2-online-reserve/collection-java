package LoveAndPetShop;
import java.util.ArrayList;
import java.time.LocalDate;
/** 
 * 我的宠物店类创建
 *
 * @author xumostar
 * @date 2024/10/26
 */

class MyAnimalShop implements AnimalShop{
    //构造函数
    public MyAnimalShop(double balance){
        this.balance=balance;
        this.animalList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }

    //返回今日利润
    public double getProfit(){
        return profit;
    }

    //返回今日余额
    public double getBalance(){
        return balance;
    }

    //获取动物列表
    public ArrayList<Animal> getAnimalList(){
        return animalList;
    }
    
    //获取动物列表
    public ArrayList<Customer> getCustomerList(){
        return customerList;
    }

    //打印开业信息
    public void OpenShop(){
        System.out.println("\n\t欢迎来到恋与宠物店(〃'▽'〃)\n"+
                           "\tWish you have a nice day!");
        System.out.println("本店的小动物：\n"+animalList);
    }

    @Override
    public void buyNewAnimal(Animal animal)throws InsufficientBalanceException{
        //如果余额不足则抛出异常
        if(this.balance<animal.animalPrice){
            throw new InsufficientBalanceException("余额不足，无法购买"+animal.animalName);
        }

        this.animalList.add(animal);
        this.balance-=animal.animalPrice;
        this.profit-=animal.animalPrice;
    }

    //卖出动物
    public void sendAnimal(Animal animal)throws AnimalNotFountException{
        if(animalList.size()==0){
            throw new AnimalNotFountException("非常抱歉，店内目前动物已售空");
        }
        //如果找得到动物就卖出
        if(animalList.contains(animal)){
            //余额加入卖出动物的钱，动物列表中删除动物
            balance+=animal.getSellPrice();
            this.profit+=animal.getSellPrice();
            animalList.remove(animal);
        }

        //找不到就抛出异常
        else{
            throw new AnimalNotFountException("非常抱歉，店内暂时没有该动物");
        }
    }

    //加入以前的老顾客
    public void addOldCustomer(Customer customer){
        customerList.add(customer);
    }

    //更新顾客列表
    public void upgrateCustomerList(Customer customer){
        //如果找得到顾客（即来过）
        if(customerList.contains(customer)){
            //访问次数加1
            customer.setVisitFrequency(customer.getFrequency()+1);
            //最近来访时间改为今天
            customer.setLastVisitDate();
            return;
        }

        //如果顾客没来过
        else{
            //访问次数加1
            customer.setVisitFrequency(customer.getFrequency()+1);
            //把顾客加入列表
            customerList.add(customer);
            return;
        }
    }

    //招待顾客
    @Override
    public void serveCustomer(Customer customer,Animal animal){
        //更新顾客列表
        upgrateCustomerList(customer);
        //顾客买走动物
        sendAnimal(animal);
    }

    //从顾客列表中筛选出当天光临的顾客并输出
    public void chooseCustomer(){
        for(int i=0;i<customerList.size();i++){
            //筛选出当天关顾的顾客
            if(customerList.get(i).getLastVisiDate().equals(LocalDate.now())){
                
                System.out.println(customerList.get(i));
            }
        }
    }

    @Override
    public void closeShop(){
        System.out.println("\t宠物店打烊了QAQ");
        System.out.println("今日光顾客人：");
        chooseCustomer();
        System.out.println("今日营业利润为："+profit);
    }

    private double profit;
    private double balance; //余额
    private ArrayList<Animal> animalList;
    private ArrayList<Customer> customerList;
    
}
