import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimalShopTest {
    public static void main(String[] args) {
        MyAnimalShop myAnimalShop = new MyAnimalShop();
        ArrayList<Animal> animalList = new ArrayList<>();
        Cat cat  = new Cat("小猫",5,"男",200);
        ChineseDog chineseDog =new ChineseDog("小狗",4,"男",100,false);
        Snake snake = new Snake("小蛇",3,"女",150,false);
        animalList.add(cat);
        animalList.add(chineseDog);
        animalList.add(snake);
        //宠物列表
        ArrayList<Customer> customerList = new ArrayList<>();
        Customer cus = new Customer();
        LocalDate lD = LocalDate.of(2024,10,22);
        cus.setLastTime(lD);
        //顾客列表
        MyAnimalShop mAS = new MyAnimalShop(300,animalList,customerList,true);
        //初始3只宠物,0个顾客.
        Scanner scanner = new Scanner(System.in);
        loop1:while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("1选项为购入动物-------2选项为接待客人------请输入你的选择");
            int choose = sc.nextInt();
            loop2:while (true) {
                switch (choose) {
                    case 1:
                        System.out.println("请输入你想要的动物 chineseDog,cat,snake");
                        String buyingChoose = sc.next();
                        switch (buyingChoose) {
                            case "chineseDog":
                                mAS.buyAnimal(chineseDog);
                                System.out.println(mAS.getRemainingMoney());
                                break;
                            case "cat":
                                mAS.buyAnimal(cat);
                                System.out.println(mAS.getRemainingMoney());
                                break;
                            case "snake":
                                mAS.buyAnimal(snake);
                                System.out.println(mAS.getRemainingMoney());
                                break;
                            default:
                                System.out.println("目前没有");
                                break;
                        }
                        break;
                    case 2:
                        Customer customer = new Customer();
                        System.out.println("请输入你的名字");
                        customer.setName(sc.next());
                        mAS.attendCustmer(customer);
                        break;
                }
                System.out.println("是否继续 true or false");
                boolean going = sc.nextBoolean();
                if (going){
                    break loop2;
                } else {
                    break loop1;
                }
            }
        }
        if (cus.getLastTime().isEqual(LocalDate.now())) {

            myAnimalShop.closeShop();
            System.out.println("余额有" + mAS.getRemainingMoney());
            for (int i = 0; i < animalList.size(); i++) {
                System.out.println("目前的宠物有" + animalList.get(i).getName());
            }
            for (int i = 0; i < customerList.size(); i++) {
                System.out.println("今天的顾客有" + customerList.get(i).getName());
            }
        }
    }
}
