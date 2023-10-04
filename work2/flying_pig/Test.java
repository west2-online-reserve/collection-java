import java.time.LocalDate;
import java.util.Scanner;
import java.util.concurrent.ThreadPoolExecutor;

public class Test {
    //动物店的一天
    public static void main(String[] args){
        AnimalShop animalShop=new MyAnimalShop();
        //先进行一天的采购
        Scanner scanner=new Scanner(System.in);
        System.out.println("开始购买动物啦，你现在有100块钱");
        System.out.println("输入1购买猫，输入2购买中华田园犬，猫一只6块，狗一只2块，按0停止采购");
        while(true){
            int type=scanner.nextInt();
            if(type==1){
                Cat cat=new Cat("一只猫",4,true,6);
                animalShop.buyAnimal(cat);
            }else if(type==2){
                ChineseRuralDog chineseRuralDog=new ChineseRuralDog("一只狗",4,true,2);
                animalShop.buyAnimal(chineseRuralDog);
            }else if (type==0){
                break;
            }else {
                System.out.println("输入错误，请重新输入");
            }
        }
        //然后顾客进来挑选
        System.out.println("第一个顾客带了100块进来购买,他今天第一次来访");
        Customer customer=new Customer("flyingpig",0, LocalDate.now());
        animalShop.entertainingCustomer(customer);
        //今天生意不好，就来了一个顾客
        System.out.println("今天生意不好，就来了一个顾客");
        animalShop.exit();
    }
}
