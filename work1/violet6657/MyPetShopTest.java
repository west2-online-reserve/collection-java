import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class MyPetShopTest {
    private static MyPetShop myPetShop;
    public static void main(String[] args) {
        ArrayList<Animal>animals= new ArrayList<>();
        animals.add(new Cat("哈基米",5,18,"母",666));
        animals.add(new ChineseDog("小狗小小",5,true,34,"公",666));
        ArrayList<Customer>customers= new ArrayList<>();
        customers.add(new Customer("林佳怡test",23, LocalTime.of(12,3,22)));
        customers.add(new Customer("胖宝宝test",3, LocalTime.of(13,5,22)));

        //创建宠物商店对象
        myPetShop = new MyPetShop(100000,animals,customers);
        while (true){
            petStart(myPetShop,animals,customers);
        }

    }











































    public static void petStart(MyPetShop myPetShop,ArrayList<Animal>animals,ArrayList<Customer>customers) {
        Scanner sc = MyPetShop.in;
        System.out.println("""
                欢迎您来到您的宠物商店,请选择的您的操作
                1:买入新动物
                2:招待客户
                3:闭店
                """);
        int choiceOperation = sc.nextInt();
        switch(choiceOperation) {
            case 1->{
                myPetShop.checkTime();
                if(!myPetShop.Working){
                    myPetShop.closeShop();
                }
                System.out.println("""
                        请问您要买什么动物?
                        1->鸡
                        2->猫咪
                        3->中华田园犬
                        4->退出
                        请输入要购买的编号""");

                int choiceAnimal = sc.nextInt();
                switch(choiceAnimal) {
                    case 1->{
                        try {myPetShop.buyNewAnimal(new Chicken ());}
                        catch (InsufficientBalanceException e) {
                            System.out.println("金额不足error");
                        }
                    }
                    case 2->{
                        try{myPetShop.buyNewAnimal(new Cat ());}
                        catch (InsufficientBalanceException e) {
                            System.out.println("金额不足error");
                        }

                    }
                    case 3->{
                        try {
                            myPetShop.buyNewAnimal(new ChineseDog ());
                        }
                        catch (InsufficientBalanceException e) {
                            System.out.println("金额不足error");
                        }

                    }
                    case 4-> petStart(myPetShop,animals,customers);
                    default -> System.out.println("您的输入不合法,请重新输入");
                }

            }
            case 2->{
                myPetShop.checkTime();
                if(!myPetShop.Working){
                    myPetShop.closeShop();
                }try {myPetShop.tradeCustomer(customers,animals);}
                catch (Exception e) {
                    System.out.println("没有动物error");
                }


            }
            case 3-> myPetShop.closeShop();
        }
    }

    public static MyPetShop getMyPetShop() {
        return myPetShop;
    }

}
