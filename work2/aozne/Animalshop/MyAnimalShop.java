import javax.naming.InsufficientResourcesException;
import java.util.ArrayList;
import java.util.Scanner;

class MyAnimalShop implements AnimalShop{
// 设置变量！
    private double money;
    private boolean isClosed;
    private double profit=0;
    private ArrayList<Animal> animals=new ArrayList<>();
    private ArrayList<Customer> customers=new ArrayList<>();
// 构造方法！
    public MyAnimalShop(double money,boolean isClosed){
        this.money=money;
        this.isClosed=isClosed;
    }
// set 与 get方法！
    public void setAnimals(ArrayList<Animal> animals){
        this.animals=animals;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setTraded(boolean closed) {
        this.isClosed = closed;
    }
    public ArrayList<Animal> getAnimals(){
        return animals;
    }
    public ArrayList<Customer> getCustomers(){
        return customers;
    }

    public double getMoney() {
        return money;
    }

    public double getProfit() {
        return profit;
    }

    public boolean isClosed() {
        return isClosed;
    }
    // 进货！
    public void purchase(Animal animal, int num) {
        double BuyMoney = num *animal.animalPrice;
        if(num<=0){
            System.out.println("购买不合法");
        }
        else if(money<=BuyMoney){
            throw new InsufficientBalanceException("余额不足，请在考虑考虑吧");
        }
        else{
            for(int i=0;i<num;i++){
                System.out.println("购买成功"+"\n该宠物信息为："+animal.toString());
                animals.add(animal);
                setMoney(money-animal.animalPrice);
                System.out.println("店内现有"+animals.size()+"只宠物");
            }
            System.out.println("余额还有"+getMoney());
        }
    }
//招待客人 并 实现购买！
    @Override
    public void hello(Customer customer) {
        customers.add(customer);
        Scanner sc=new Scanner(System.in);
        if (isClosed == true) {
            System.out.println("不好意思本店已打烊");
        }
        else if (animals.size()==0) {
            throw new AnimalNotFoundException("本店宠物已经卖光了，请明天再来吧");
        }
        else {
            System.out.println("欢迎" + customer.customerName + "光临");
            System.out.println("以下为本店所有的宠物");
            for (Animal animal : animals) {
                System.out.println(animal);

            }
            System.out.println("购买中华田园犬请输入1\n购买猫请输入2\n进来看看不买请输入3");
            int options = sc.nextInt();
            switch (options) {
                case 1:
                    System.out.println("请输入你要买的数量");
                    int num0 = sc.nextInt();
                    int count1=0;
                    for(int i=0;i<animals.size();i++){
                        if(animals.get(i).animalspecies.equals("dog")){
                            count1++;
                        }
                    }
                    if (num0 > count1) {
                        System.out.println("购买数量超过本店所有中华田园犬数，请在考虑一下吧");
                    } else {
                        profit = num0 * 100;
                        int count2=0;
                        for (int i = animals.size()-1;i>=0; i--) {
                            if(animals.get(i).animalspecies.equals("dog")){
                                System.out.println(animals.get(i));
                                count2++;
                                animals.remove(i);
                                if(count2==num0){
                                    break;
                                }
                            }
                        }
                        break;
                    }
                case 2:
                    System.out.println("请输入你要买的数量");
                    int num = sc.nextInt();
                    int count3=0;
                    for(int i=0;i<animals.size();i++){
                        if(animals.get(i).animalspecies.equals("cat")){
                            count3++;
                        }
                    }
                    if (num > count3) {
                        System.out.println("购买数量超过本店所有猫猫数，请在考虑一下吧");
                    } else {
                        profit = num * 50;
                        int count4=0;
                        for (int i = animals.size()-1;i>=0; i--) {
                            if(animals.get(i).animalspecies.equals("cat")){
                                System.out.println(animals.get(i));
                                count4++;
                                animals.remove(i);
                                if(count4==num){
                                    break;
                                }
                            }
                        }
                        break;
                    }
            }

        }
    }
//废稿 不用看 留着 让我想想可不可以改！
    /*public void buy(Animal animal,int num){
        if(animals.size()>0) {
            if (num > animals.size()) {
                System.out.println("购买数量超过宠物店该种动物数量,请购买少于" + animals.size() + "只该种宠物");
            } else if (isClosed == false) {
                System.out.println("不好意思本店已打烊，请明天再来购买吧");
            } else {
                profit = num*(animal.animalPrice - animal.animalCost);
                System.out.println("购买成功" + animal.toString());
            }
        }
        else{
            isClosed=true;
            System.out.println("宠物店已打烊了，请每天再来吧");
        }
    }*/
//关店 顾客信息 利息！
    @Override
    public void closeAnimalShop(AnimalShop animalShop) {
        this.isClosed=true;
        for(int i=0;i<customers.size();i++){
            int count=i+1;
            System.out.println("今天第"+count+"位顾客信息为"+customers.toString()+"\n");
        }
        System.out.println("今天利息为"+profit);
    }
}