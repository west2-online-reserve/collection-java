package shop;

import java.util.Scanner;

/**
 *
 * 我存在的问题:
 * 在这个项目中,我没有用到多态.我会用多态,但不知道什么时候有用
 * ....懂了!使用多态能使程序有更好的可维护性和可拓展性
 *
 * @author HarveyBlocks
 * @date 2023/09/03 15:15
 **/

public class Test {
    public static void main(String[] args) {
        demo();
    }
    //输入检查+购买
    public static void demo() {
        MyAnimalShop myAnimalShop = new MyAnimalShop();
        myAnimalShop.shutDown();
        while (myAnimalShop.isOpen()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("店现在正开张,你想要实现哪一项活动");
            System.out.print(
                    "1.购买宠物\n" +
                            "2.招待顾客\n"+
                            "3.查看店铺信息\n"+
                            "请输入你的选择:"

                    );
            int choose;
            if(scanner.hasNextInt()){
                choose = scanner.nextInt();
            }else {
                System.out.println("输入不合法,请重新输入");
                continue;
            }

            switch (choose){
                case 1:
                    try{
                        myAnimalShop.purchase(scanner);
                    }catch (Exception e){e.printStackTrace();}
                    break;
                case 2:
                    try{
                        myAnimalShop.serve(scanner);
                    }catch (Exception e){e.printStackTrace();}
                    break;
                case 3:
                    System.out.println(myAnimalShop);
                    break;
                default:
                    System.out.println("输入超范围请重新输入");
                    continue;
            }
            myAnimalShop.shutDown();

        }

    }
}
