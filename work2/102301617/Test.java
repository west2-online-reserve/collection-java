
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {

        MyAnimalShop animalShop = new MyAnimalShop();
        /*买入动物*/
        Scanner scanner = new Scanner(System.in);
        System.out.println("现有资产1500元");

        System.out.println("输入1购买猫，输入2购买土狗，输入3购买Jerry，输入0则停止采购");
        //------------------------------------------------------------------------------------------------------------------------------------------

        while (true) {

            int type = scanner.nextInt();


            if (type == 1) {
                Boolean define = false;

                while (define == false) {
                    System.out.println("公滴母滴");

                 String type1 = scanner.next();
                    if (type1.equals(MyAnimalShop.male) || type1.equals(MyAnimalShop.female)) {
                        Cat cat = new Cat("一只咪", 0, 200, type1);
                        animalShop.perchas(cat);
                        define = true;
                    } else {
                        System.out.println("请输入公或者母");

                    }

                }
            } else if (type == 2) {
                System.out.println("公滴母滴");
                Boolean define = false;
                while (define == false) {
                    String type2 = scanner.next();
                    if (type2.equals(MyAnimalShop.male) || type2.equals(MyAnimalShop.female)) {
                        ChineseGardenDog chineseGardenDog = new ChineseGardenDog("修勾", 0, 100, type2);
                        animalShop.perchas(chineseGardenDog);
                        define = true;
                    } else {
                        System.out.println("请输入公或者母");
                    }
                }
            } else if (type == 3) {
                Boolean define = false;
                System.out.println("公滴母滴");
                while (define == false) {
                    String type3 = scanner.next();
                    if (type3.equals(MyAnimalShop.male) || type3.equals(MyAnimalShop.female)) {
                        Jerry jerry = new Jerry("鼠", 0, 1000, type3);
                        animalShop.perchas(jerry);
                        define = true;
                    } else {
                        System.out.println("请输入公或者母");
                    }
                }
            } else if (type == 0) {
                System.out.println("已停止采购");
                System.out.println("现有动物：");
                System.out.println(animalShop.list);
                break;

            } else {
                System.out.println("你干嘛~哎呦");
            }

        }
        //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
        /*招待顾客*/
        System.out.println("开始营业啦！");
        System.out.println("按1招待顾客");
        while (true) {
            int open = scanner.nextInt();
            if (open==1) {
           Customer customer= new Customer();
                System.out.println("询问得知顾客姓名：");
           customer.name=scanner.next();
           animalShop.entertaining(customer);
                System.out.println("如果想罢工就按2吧");
            }else if(open==2){
                break;}
            else{
                System.out.println("给我按1！！！");
            }

            }
        animalShop.close();

        }
    }
