import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyAnimalShop implements AnimalShop{
    private double balance; //余额
    private double profit = 0; //利润，初始化为0
    private List<Animal> animalList = new ArrayList<Animal>(); //动物
    private List<Customer> customerList = new ArrayList<Customer>(); //顾客
    private boolean openStatus = false; //营业状态


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
        System.out.println("【店铺状态】");
        System.out.printf("店内余额为%.1f元\n", this.balance);
        //如果店铺【未开张】，不能显示收入
        try{
            if(!openStatus){
                throw new ShopNotOpen("[warning: 店铺未开张]");
            }
            System.out.printf("今日收入了%.1f元\n\n", this.profit);
        } catch (ShopNotOpen e) {
            System.out.println(e.getMessage() + " 无法显示收入\n");
        }

        //如果【没有动物】，不能显示动物列表
        try {
            if (animalList.isEmpty()) {
                throw new AnimalNotFoundException("[warning: 店内没有动物]");
            }
            System.out.println("你的店铺目前拥有动物的信息：");
            for (Animal animal : animalList) {
                System.out.println(animal.toString());
                System.out.println();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void buyAnimals(Animal animal){
        //如果【余额不足】，报错
        try{
            if(this.balance < animal.price){
                throw new InsufficientBalanceException
                        ("[warning: 商店没有足够的余额购买该动物！！！！]\n");
            }
            this.balance -= animal.cost;
            this.profit -= animal.cost;
            this.animalList.add(animal);
            System.out.println("【商店已成功购买了一只动物】\n动物信息：\n" + animal);
            System.out.println("余额为" + this.balance + "\n");
        } catch (Exception e) {
            System.out.print(e.getMessage() + animal.name + "的价格为：" +
                    animal.price + "，而商店的余额仅剩余：" + this.balance + "\n\n");
        }
    }

    @Override
    public void greetCustomer(Customer customer) throws AnimalNotFoundException{
        //如果店铺【未开张】，且顾客【今天】到访，报错
        try {
            if (!openStatus && customer.recentDate.equals(LocalDate.now())) {
                throw new ShopNotOpen("[warning: 店铺未开张]");
            }
            customerList.add(customer);

            //顾客选择动物
            CustomerChooseAnimal(customer);

        } catch (ShopNotOpen e) {
            System.out.println(e.getMessage());
            System.out.printf("%s无法进入店铺\n\n", customer.getName());
        }
    }


    public void CustomerChooseAnimal(Customer customer){
        try{
            if (animalList.isEmpty()){
                throw new AnimalNotFoundException("[warning: 店内没有动物]\n");
            }
            //生成随机索引，客户将会在索引范围内随机选择动物购买

            //这样做Animal并不是客户决定的，但我暂时不知道该怎么做。。     : (
            Random r = new Random();
            int index = r.nextInt(animalList.size());
            Animal animal = animalList.get(index);

            //顾客进行购买操作
            if(customer.recentDate.equals(LocalDate.now())){
                System.out.println("顾客" + customer.getName() + "【已购买了动物】\n动物信息:");
                System.out.println(animal.toString() + "\n");
                animalList.remove(index);
            }else{
                System.out.println("顾客" + customer.getName() + "【已预约了动物】\n动物信息:");
                System.out.println(animal.toString() + "\n");
                animalList.remove(index);
            }

            //余额增加，计算收入，只有【今天】进入店铺的顾客消费才应该记录
            if(customer.recentDate.equals(LocalDate.now())){
                this.balance += animal.price;
                this.profit += animal.price;
                System.out.printf("现在商店余额为%.1f\n\n", balance);
                animalList.remove(animal);
            }
        }catch (Exception e){
            System.out.print(e.getMessage());
            System.out.printf("顾客%s现在无法购买动物\n\n", customer.getName());
        }
    }

    public void openShop(){
        openStatus = true;
        System.out.println("【店铺开张！】\n");
    }

    @Override
    public void shopClose(){
        System.out.println("【今日总结】");
        System.out.printf("今日收入了%.1f元, ", this.profit);
        System.out.printf("店内余额为%.1f元\n", this.balance);
        System.out.println("【今日前来购买过的顾客信息】");

        for(Customer customer : customerList) {
            //只输出【今天】到访的顾客
            if(customer.recentDate.equals(LocalDate.now())){
                System.out.println(customer.toString());
                System.out.println("----------");
                this.openStatus = false;
            }

        }
    }


}
