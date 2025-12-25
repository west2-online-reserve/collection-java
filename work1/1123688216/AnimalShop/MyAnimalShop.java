import java.io.FilterOutputStream;
import java.time.LocalDate;
import java.util.*;

import Exception.InsufficientBalanceException;
import Exception.AnimalNotFoundException;
public class MyAnimalShop implements AnimalShop{
    protected double balance;//余额
    protected ArrayList<Animal> animalsList;
    protected Set<Customer> customersList;
    protected double profit;//当天利润
    public MyAnimalShop(double balance) {

        this.balance = balance;
        this.profit  = 0;
        this.animalsList=new ArrayList<>();
        this.customersList=new HashSet<>();
    }

    @Override
    public void buyNewAnimal(Animal animal)  {
        if(animal.cost>balance){
            throw new InsufficientBalanceException();
        }else{
            animal.setPrice(animal.getCost()*1.5);//宠物的定价为成本的1.5倍
            this.animalsList.add(animal);
            this.balance -= animal.cost;
            System.out.println("购买了"+animal.name+",当前店铺余额还剩下:"+this.balance);
        }
    }

    @Override
    public void treatCustomer(Customer customer) {
        if(customer.getVisitCount()==0){
            System.out.println("注意注意！！该客户是首次光临本店");
        }
        else{
            System.out.println("当前客户的姓名为:"+customer.getName()+",来过店里"+customer.getVisitCount()+"次,上次到访日期为:"+customer.getLastestVisitTime());
        }
        customer.setVisitCount(customer.getVisitCount()+1);
        customer.setLastestVisitTime(LocalDate.now());
        this.customersList.add(customer);
        boolean success = false;//用来确认客户是否继续购买宠物。
        System.out.println("欢迎光临，请问需要买什么宠物？");
        while(!success){
            try{
                if(this.animalsList.isEmpty()){
                    success=true;
                    throw new AnimalNotFoundException();

                }
                System.out.print("我们这边的动物有:");
                for(int i=0;i<this.animalsList.size();i++) {
                    System.out.println(i + ":" + this.animalsList.get(i).toString());
                }

                System.out.println("请输入您想购买的宠物序号");
                Scanner sc = new Scanner(System.in);
                int number = sc.nextInt();
                if(number<0||number>=animalsList.size()){
                    throw new IndexOutOfBoundsException("序号"+number+"不存在,请重新输入");
                }

                System.out.println("请确定要购买的宠物信息,确认购买请输入1，其他任意按键取消购买");
                System.out.println(this.animalsList.get(number).toString());
                int affirmNumber = sc.nextInt();
                if(affirmNumber==1){
                    System.out.println("恭喜您购买宠物成功");
                    Animal animal = this.animalsList.get(number);
                    this.profit +=animal.price-animal.cost;//计算利润
                    this.balance +=animal.price;
                    this.animalsList.remove(number);//购买的宠物从宠物列表中移除
                }
                else {
                    System.out.println("取消购买");
                }
                System.out.println("如果你需要继续购买请按1，其他任意按键取消购买");
                int continueNumber = sc.nextInt();
                if(continueNumber==1){
                    success=false;
                }
                else {
                    success=true;
                }
            }catch(AnimalNotFoundException e){
                System.err.println(e.getMessage());
            }catch(IndexOutOfBoundsException e){
                System.err.println(e.getMessage());
            }catch(InputMismatchException e){
                System.err.println("输入格式有误，请单独输入一个数字");
            }

        }
        System.out.println("欢迎下次光临");
    }

    @Override
    public void outOfBusiness() {
        for(Customer customer:this.customersList){
            if(customer.lastestVisitTime.equals(LocalDate.now())){
                System.out.println("客人信息:"+customer.toString());
            }
        }
        System.out.println("今天日期为"+LocalDate.now()+"利润为:"+this.profit);
        this.profit = 0;
    }
}
