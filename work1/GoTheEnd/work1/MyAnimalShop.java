import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyAnimalShop implements AnimalShop{
    private double balance; //余额
    private double profit = 0; //利润，初始化为0
    private List<Animal> animalList = new ArrayList<Animal>(); //动物
    private List<Customer> customerList = new ArrayList<Customer>(); //顾客
    private boolean openStatus; //营业状态


    public MyAnimalShop(double balance) {
        this.balance = balance;
    }

    public MyAnimalShop(double balance, List<Animal> animalList) {
        this.balance = balance;
        this.animalList = animalList;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }


    public double getBalance() {
        return balance;
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    //查看当前店铺状态
    public void showShopStatus(){
        System.out.printf("店内余额为%.1f元\n", this.balance);
        System.out.printf("今日收入了%.1f元\n\n", this.profit);
        if (animalList.isEmpty()){
            System.out.println("你的店铺没有动物。。。");
        }else {
            System.out.println("你的店铺目前拥有动物的信息：");
            for (Animal animal : animalList) {
                System.out.println(animal.toString());
            }
        }
    }

    @Override
    public void buyAnimals(Animal animal) throws InsufficientBalanceException{
        //如果余额不足，报错
        try{
            if(this.balance < animal.price){
                throw new InsufficientBalanceException
                        ("【商店没有足够的余额购买该动物！！！！】");
            }
            this.balance -= animal.cost;
            this.profit -= animal.cost;
            this.animalList.add(animal);
            System.out.println("商店已成功购买了一只动物，动物信息：\n" + animal);
            System.out.println("余额为" + this.balance + "\n");
        } catch (Exception e) {
            System.out.print(e.getMessage() + animal.name + "的价格为：" +
                    animal.price + "，而商店的余额仅剩余：" + this.balance + "\n\n");
        }
    }

    @Override
    public void greetCustomer(Customer customer) throws AnimalNotFoundException{
        //如果没有动物，报错
        try{
            if (animalList.isEmpty()){
                throw new AnimalNotFoundException("抱歉，动物已经卖完了\n");
            }
            //生成随机索引，客户将会在索引范围内随机选择动物购买
            Random r = new Random();
            int index = r.nextInt(animalList.size());
            Animal animal = animalList.get(index);
            //顾客进入，并进行购买操作
            customerList.add(customer);
            System.out.println("顾客" + customer.getName() + "已购买了动物，动物信息:");
            System.out.println(animal.toString());
            //余额增加，计算收入
            this.balance += animal.price;
            this.profit += animal.price;
            System.out.printf("现在商店余额为%.1f\n\n", balance);
            animalList.remove(animal);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void shopClose(){
        System.out.printf("今日收入了%.1f元, ", this.profit);
        System.out.printf("店内余额为%.1f元\n", this.balance);
        System.out.println("【前来购买的顾客信息】");
        for(Customer customer : customerList) {
            System.out.println(customer.toString());
            System.out.println("----------");
            this.openStatus = false;
        }
    }
}
