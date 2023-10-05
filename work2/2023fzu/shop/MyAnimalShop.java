package shop;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalTime;
import java.util.*;

/**
 * @author HarveyBlocks
 * @date 2023/08/15 13:38
 **/

public class MyAnimalShop implements AnimalShop{

    private double balance;//店的余额
    private ArrayList<Animal> animals = new ArrayList<>();//一个存放动物的列表
    private ArrayList<Customer> customers = new ArrayList<>();//一个顾客列表留作纪念
    private boolean isOpen;//是否正在营业
    public MyAnimalShop(){
        this(10000);
    }
    public MyAnimalShop(double balance){
        this.balance = balance;
    }


    protected static final int SEX_RANDOM = 1;

    //买入新动物,花钱,买了新动物之后才能知道
    @Override
    public void purchase(Scanner scanner) {
        ShopAction.purchaseAction(this,scanner);
    }


    @Override
    public void serve(Scanner scanner) {
            ShopAction.serveAction(this,scanner);
    }




    @Override
    public String toString() {
        return "MyAnimalShop{" +
                "\nbalance=" + balance +
                "\n, animal=" + animals +
                "\n, customer=" + customers +
                "\n, isOpen=" + isOpen +
                "\n}";
    }

    protected static final int OPEN_TIME = 9;
    protected static final int CLOSE_TIME = 17;
    //歇业
    @Override
    public void shutDown() {
        ShopAction.shutDownAction(this);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}



class ShopAction {
    public static void shutDownAction(MyAnimalShop myAnimalShop){
        LocalTime time = LocalTime.now();

        if (time.getHour() > MyAnimalShop.CLOSE_TIME || time.getHour() < MyAnimalShop.OPEN_TIME) {//朝九晚五的乌托邦作息
            myAnimalShop.setOpen(false);
            System.out.println("店关门啦");
            System.out.println("现在店内信息为:");
            System.out.println(myAnimalShop);
        } else {
            myAnimalShop.setOpen(true);
        }
    }
    //遍历animal,返回一类动物的信息供人选择
    public static boolean printList(MyAnimalShop myAnimalShop,Class variety){
        boolean flag = false;
        for (Animal everyAnimal:myAnimalShop.getAnimals()) {
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
    public static void serveAction(MyAnimalShop myAnimalShop,Scanner scanner){
        if(myAnimalShop.getAnimals().isEmpty()){
            System.out.println("你还没有pet可售卖,快去进货吧");
            return;
        }

        System.out.print("输入客户姓名:");
        scanner.nextLine();//吸收那个不知道啥的字符,它就是会出来,也不知道是啥
        String name = scanner.nextLine();
        Customer customer1;
        if(myAnimalShop.getCustomers().contains(new Customer(name))){
            customer1 = myAnimalShop.getCustomers().get(myAnimalShop.getCustomers().indexOf(new Customer(name)));
            customer1.plusCount();
        }
        else {
            customer1 = new Customer(name);
            myAnimalShop.getCustomers().add(customer1);
        }


        System.out.print(
                """
                        顾客可以选择下列动物:
                        1.兔子:50元
                        2.猫:200元
                        3.中华田园犬:100元
                        4.放弃购买
                        请输入你的选择:"""
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
            switch (choose) {
                case 1 -> {
                    isFind = printList(myAnimalShop, Rabbit.class);
                    flag = false;
                }
                case 2 -> {
                    isFind = printList(myAnimalShop, Cat.class);
                    flag = false;
                }
                case 3 -> {
                    isFind = printList(myAnimalShop, ChineseDog.class);
                    flag = false;
                }
                case 4 -> {
                    System.out.println("已放弃");
                    flag = false;
                    return;
                }
                default -> System.out.print("输入超范围请重新输入:");
            }
            if (!isFind){
                System.out.println("您的余额是:"+ myAnimalShop.getBalance());
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
            for (Animal everyAnimal: myAnimalShop.getAnimals()) {
                if (everyAnimal.getName().equals(chooseName)) {
                    myAnimalShop.getAnimals().remove(everyAnimal);
                    isFound = true;
                    myAnimalShop.setBalance(myAnimalShop.getBalance() + everyAnimal.getPrice());
                    System.out.println("售卖成功");
                    myAnimalShop.getAnimals().remove(everyAnimal);
                    break;
                }
            }

            if (! isFound){
                throw new AnimalNotFountException("无此名的动物,请重新输入");
            }
            firstTime = false;

        }
        System.out.println("您的余额是:"+ myAnimalShop.getBalance());
    }
    public static void purchaseAnimal(MyAnimalShop myAnimalShop,Class animalClass)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Constructor animalConstructor = animalClass.getDeclaredConstructor(String.class, int.class, int.class);
        Animal purAnimal = (Animal) animalConstructor.newInstance(
                "dog" + (ChineseDog.count + 1), getRandom(ChineseDog.AGE_RANDOM), getRandom(MyAnimalShop.SEX_RANDOM)
        );
        myAnimalShop.getAnimals().add(purAnimal);
        if (myAnimalShop.getBalance() > purAnimal.getPrice()) {
            myAnimalShop.setBalance(myAnimalShop.getBalance() - purAnimal.getPrice());
            System.out.println("购买成功");
        } else {
            throw new InsufficientBalanceException("余额不足");
        }
    }
    //给一个随机数的上限(包括),返回一个随机数
    public static int getRandom(int high){
        Date date = new Date();
        long time = date.getTime();//自动拆箱
        Random random = new Random(time);
        return random.nextInt(high+1);
    }

    public static void purchaseAction(MyAnimalShop myAnimalShop,Scanner scanner){
        System.out.println("您的余额是:"+ myAnimalShop.getBalance());
        System.out.print(
                """
                        您可以选择下列动物:
                        1.兔子:40元
                        2.猫:160元
                        3.中华田园犬:80元
                        4.放弃购买
                        请输入你的选择:"""
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

            switch (choose) {
                case 1 -> {
                    try {
                        ShopAction.purchaseAnimal(myAnimalShop, Rabbit.class);
                    } catch (Exception e) {
                        System.out.println("ChinesePurchaseException");
                    }
                    flag = false;
                }
                case 2 -> {
                    try {
                        ShopAction.purchaseAnimal(myAnimalShop, Cat.class);
                    } catch (Exception e) {
                        System.out.println("ChinesePurchaseException");
                    }
                    flag = false;
                }
                case 3 -> {
                    try {
                        ShopAction.purchaseAnimal(myAnimalShop, ChineseDog.class);
                    } catch (Exception e) {
                        System.out.println("ChinesePurchaseException");
                    }
                    flag = false;
                }
                case 4 -> {
                    System.out.println("已放弃");
                    flag = false;
                }
                default -> System.out.print("输入超范围请重新输入:");
            }
        }

        System.out.println("您的余额是:"+ myAnimalShop.getBalance());
    }
}
