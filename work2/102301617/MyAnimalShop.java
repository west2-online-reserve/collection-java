import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author 102301617
 */
@SuppressWarnings("ALL")
public class MyAnimalShop implements AnimalShop {


/**动物列表*/
    ArrayList list = new ArrayList();
    /**顾客列表*/
    ArrayList costom=new ArrayList();
    /**是否营业*/
    Boolean run=false;
    double profits;
   final double assets=1500;
   /**用于规范输入*/
    static String male = "公";
    static String female = "母";


    public Animal animal;
     double balance = 1500;

    @Override
    public void perchas (Animal animal) {

        try {
            if (run) {
                System.out.println("宠物店未在运营状态\n");
                return;}
                if (balance >= animal.cost) {
                    balance -= animal.cost;
                    list.add(animal);
                    System.out.println("购买成功");
                    System.out.println("余额："+balance);

                } else {
                    throw new InsufficientBalanceException();

                }



        } catch (InsufficientBalanceException e) {
            System.out.println(e.toString());

        }
    }
    @Override
    public void entertaining(Customer customer) {
        Scanner scanner=new Scanner(System.in);


        try {
            if (run) {
                System.out.println("宠物店已打烊\n");


            }
            if (!list.isEmpty()) {
                costom.add(customer);
                System.out.println("顾客购买的宠物为");
                int number=(int)(Math.random()*list.size());
                Animal animal=(Animal) list.get(number);
                list.remove(number);
                balance+=animal.price*number;
                System.out.println(animal);
                customer.visit++;
                customer.time= LocalDate.now();

            }else{throw new AnimalNotFountException();}
        }catch (AnimalNotFountException E){
            System.out.println(E.toString());
        }


    }

    @Override
    public void close() {
        System.out.println(costom);
        profits=assets-balance;
        System.out.println("今天赚了"+profits+"元");
        System.out.println("有以下顾客光顾");
        System.out.println(costom);
        System.out.println("以下是宠物店剩余动物");
        System.out.println(list);

    }
}
