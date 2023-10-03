package shop;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author HarveyBlocks
 * @date 2023/08/15 13:38
 **/
public class MyAnimalShop implements AnimalShop{

    private double balance;//店的余额
    private ArrayList<Animal> animal = new ArrayList<>();//一个存放动物的列表
    private ArrayList<Customer> customer = new ArrayList<>();//一个顾客列表留作纪念
    private boolean isOpen;//是否正在营业
    public MyAnimalShop(){
        this(10000);
    }
    public MyAnimalShop(double balance){
        this.balance = balance;
    }
    //给一个随机数的上限(包括),返回一个随机数
    public static int getRandom(int high){
        Date date = new Date();
        long time = Long.valueOf(date.getTime());//自动拆箱
        Random random = new Random(time);
        return random.nextInt(high+1);
    }


    //买入新动物,花钱,买了新动物之后才能知道
    @Override
    public void purchase(Scanner scanner) {
        System.out.println("您的余额是:"+ this.balance);
        System.out.print(
                "您可以选择下列动物:\n" +
                        "1.兔子:40元\n"+
                        "2.猫:160元\n"+
                        "3.中华田园犬:80元\n"+
                        "4.放弃购买\n"+
                        "请输入你的选择:"
        );

        //选择模块,可以写成方法,不过都复制粘贴好了就算了
        boolean flag = true;
        int choose;
        while(flag){
            if(scanner.hasNextInt()){
                choose = scanner.nextInt();
            }else {
                System.out.print("输入不合法,请重新输入:");
                continue;
            }

            switch (choose){
                case 1:
                    Rabbit rabbit = new Rabbit(
                            "rabbit"+(Rabbit.count+1),getRandom(10),getRandom(1)
                    );
                    animal.add(rabbit);
                    if (this.balance>40){
                        this.balance -= 40 ;
                        System.out.println("购买成功");
                    }else {
                        throw new InsufficientBalanceException("余额不足");
                    }
                    flag =false;
                    break;
                case 2:
                    Cat cat = new Cat(
                            "cat"+(Cat.count+1),getRandom(15),getRandom(1)
                    );
                    animal.add(cat);
                    if (this.balance>160){
                        this.balance -= 160 ;
                        System.out.println("购买成功");
                    }else {
                        throw new InsufficientBalanceException("余额不足");
                    }
                    flag =false;
                    break;
                case 3:
                    ChineseDog chineseDog = new ChineseDog(
                            "dog"+(ChineseDog.count+1),getRandom(20),getRandom(1)
                    );//其实可以在Dog类里写一个无参构造,在无参构造里随机这个性别啥的,
                        // 主要是一开始想着用户能够指定性别,后来又觉得让用户指定性别啥的没啥必要,真的有人在意要养母狗还是公狗吗?
                    animal.add(chineseDog);
                    if (this.balance>80){
                        this.balance -= 80 ;
                        System.out.println("购买成功");
                    }else {
                        throw new InsufficientBalanceException("余额不足");
                    }
                    flag =false;
                    break;
                case 4:
                    System.out.println("已放弃");
                    flag = false;
                    break;
                default:
                    System.out.print("输入超范围请重新输入:");
            }
        }

        System.out.println("您的余额是:"+ this.balance);
    }

    //招待客户,要求卖出动物,获得钱
    @Override
    public void serve(Scanner scanner) {
        if(animal.size() == 0){
            System.out.println("你还没有pet可售卖,快去进货吧");
            return;
        }

        System.out.print("输入客户姓名:");
        scanner.nextLine();//吸收那个不知道啥的字符,它就是会出来,也不知道是啥
        String name = scanner.nextLine();
        Customer customer1;
        if(customer.contains(new Customer(name))){
            customer1 = customer.get(customer.indexOf(new Customer(name)));
            customer1.plusCount();
        }
        else {
            customer1 = new Customer(name);
            customer.add(customer1);
        }


        System.out.print(
                "顾客可以选择下列动物:\n" +
                        "1.兔子:50元\n"+
                        "2.猫:200元\n"+
                        "3.中华田园犬:100元\n"+
                        "4.放弃购买\n"+
                        "请输入你的选择:"
        );

        //选择模块,可以写成方法,不过都复制粘贴好了就算了
        boolean flag = true;
        int choose;
        while(flag){
            if(scanner.hasNextInt()){
                choose = scanner.nextInt();
            }else {
                System.out.print("输入不合法,请重新输入");
                continue;
            }

            boolean isFind = false;
            switch (choose){
                case 1:
                    isFind = printAnimalList(new Rabbit().getClass());
                    flag =false;
                    break;
                case 2:
                    isFind = printAnimalList(new Cat().getClass());
                    flag =false;
                    break;
                case 3:
                    isFind = printAnimalList(new ChineseDog().getClass());
                    flag =false;
                    break;
                case 4:
                    System.out.println("已放弃");
                    flag = false;
                    return;
                default:
                    System.out.print("输入超范围请重新输入:");
                    break;
            }
            if (!isFind){
                System.out.println("您的余额是:"+ this.balance);
                return;
            }
        }

        //购买,消费
        boolean isFound = false;
        boolean firstTime = true;
        while(! isFound){
            System.out.print("请输入顾客要买的pet的名字:");
            if(firstTime){
                scanner.nextLine();//我的头要掉啦!!!
            }

            String chooseName = scanner.nextLine();
            System.out.println("顾客要购买的pet名字是:"+chooseName);
            for (Animal everyAnimal:animal) {
                if (everyAnimal.getName().equals(chooseName)) {
                    animal.remove(everyAnimal);
                    isFound = true;
                    this.balance += everyAnimal.getPrice();
                    System.out.println("售卖成功");
                    animal.remove(everyAnimal);
                    break;
                }
            }

            if (! isFound){
                throw new AnimalNotFountException("无此名的动物,请重新输入");
            }
            firstTime = false;

        }
        System.out.println("您的余额是:"+ this.balance);

    }

    //遍历animal,返回一类动物的信息供人选择
    public boolean printAnimalList(Class variety){
        boolean flag = false;
        for (Animal everyAnimal:
             animal) {
            if(everyAnimal.getClass().equals(variety)){
                System.out.println(everyAnimal);
                flag  = true;
            }

        }
        if(! flag){
            System.out.println("店家还没有该钟动物,请等待店家进货");
        }
        return flag;
    }

    @Override
    public String toString() {
        return "MyAnimalShop{" +
                "\nbalance=" + balance +
                "\n, animal=" + animal +
                "\n, customer=" + customer +
                "\n, isOpen=" + isOpen +
                "\n}";
    }

    //歇业
    @Override
    public void shutDown() {
        LocalTime time = LocalTime.now();

        if(time.getHour()>20 || time.getHour() <9){//朝九晚五的乌托邦作息
            this.isOpen = false;
            System.out.println("店关门啦");
            System.out.println("现在店内信息为:");
            System.out.println(this.toString());
        }else {
            this.isOpen = true;
        }

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList getAnimal() {
        return animal;
    }

    public void setAnimal(ArrayList animal) {
        this.animal = animal;
    }

    public ArrayList getCustomer() {
        return customer;
    }

    public void setCustomer(ArrayList customer) {
        this.customer = customer;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
