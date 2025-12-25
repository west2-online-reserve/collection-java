import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import Exception.InsufficientBalanceException;
public class Test {
    public static void main(String[] args) {
        //初始化小动物
        ArrayList<Animal> initAnimalsList =initAnimals();
        //创建商店
        MyAnimalShop myAnimalShop = new MyAnimalShop(1000);//以1000块作为开店启动资金
        //购买动物
        showAndBuyAnimal(initAnimalsList,myAnimalShop);

        pause();

        //创建顾客
        Customer customer_1 = new Customer("顾客1");

        Customer customer_2 = new Customer("顾客2");

        //招待顾客
        myAnimalShop.treatCustomer(customer_1);
        //第二次到访测试
        pause();
        myAnimalShop.treatCustomer(customer_1);
        //不同的新客人到来
        pause();
        myAnimalShop.treatCustomer(customer_2);
        //歇业
        pause();
        myAnimalShop.outOfBusiness();
    }
    public static ArrayList<Animal> initAnimals(){
        ChineseRuralDog dog_1 = new ChineseRuralDog("doge",99,"男",12,true);
        ChineseRuralDog dog_2 = new ChineseRuralDog("瓜皮",66,"男",2,true);
        ChineseRuralDog dog_3= new ChineseRuralDog("猫",50,"男",2,true);

        Cat cat_1 = new Cat("耄耋",180,"男",20,false);
        Cat cat_2 = new Cat("哈吉米",127,"男",3,true);
        Cat cat_3 = new Cat("曼波",80,"女",5,true);

        ArrayList<Animal> initAnimalsList = new ArrayList<>();
        initAnimalsList.add(dog_1);
        initAnimalsList.add(dog_2);
        initAnimalsList.add(dog_3);
        initAnimalsList.add(cat_1);
        initAnimalsList.add(cat_2);
        initAnimalsList.add(cat_3);
        return initAnimalsList;
    }

    public static void showAndBuyAnimal(ArrayList<Animal> initAnimalsList,MyAnimalShop myAnimalShop){
        System.out.println("当前可以购买的动物为:");
        for(int i=0;i<initAnimalsList.size();i++){
            System.out.println(i+":"+initAnimalsList.get(i));
        }
        Scanner sc = new Scanner(System.in);
        boolean success = false;//用来检验用户输入是否符合格式
        while(!success){
            System.out.println("请输入序号购买你想要买的动物，中间以','隔开,支持重复购买");
            String numString = sc.nextLine();
            try{
                List<Integer> numList;
                numList = Arrays.stream(numString.split(","))
                        .map(Integer::parseInt)
                        .toList();
                for(int i=0;i<numList.size();i++){
                    int index = numList.get(i);
                    if(index<0||index>=initAnimalsList.size()){
                        throw new IndexOutOfBoundsException("该序号"+index+"不存在");
                    }
                    Animal animal = initAnimalsList.get(index);
                    myAnimalShop.buyNewAnimal(animal);
                }
                success = true;
            } catch (NumberFormatException e) {
                System.err.println("请确保您只输入了数字和',");
            }catch (IndexOutOfBoundsException e){
                System.err.println(e.getMessage());
            }catch (InsufficientBalanceException e){
                System.out.println("余额不足,欢迎下次再来");
                success=true;//余额不足不再购买
            }
        }
    }
    public static void pause(){
        System.out.println("\n--- 程序暂停，请按 [回车键] 继续 ---");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        System.out.println("--- 继续执行 ---");
    }
}
