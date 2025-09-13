import java.util.Scanner;

public class BoothTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("输入创建瓜摊的数量");
        int num = sc.nextInt();
        Booth[] booths = new Booth[num];
        System.out.println("id请从1依次向后输入");
        for (int i = 0; i < booths.length; i++) {
            System.out.println("依次输入id,名字,西瓜总数,整改状态");
            Long id = sc.nextLong();
            String name = sc.next();
            int tota = sc.nextInt();
            boolean state = sc.nextBoolean();
            Booth b = new Booth(id, name, tota, state);
            booths[i] = b;
        }
        System.out.println("*******创建成功*******");
        int n = -1;
            while (n !=0){
                System.out.println("*********输入对应的操作：1.对指定id摊主进行操作" +
                                                " 2.查看各位摊主的整改状态 " +
                                                " 3.输入0退出*********");
               n = sc.nextInt();
                if (n == 0)break;
                else {
                    switch (n){
                        case 1 ->{
                            System.out.println("对指定id的摊主进行操作:输入id");
                            int id = sc.nextInt();
                            if (booths[id - 1].isIsClosed() == true){
                                System.out.println("改摊已关门");
                            }else {
                                System.out.println("是否进行销售（true or false）");
                                boolean b = sc.nextBoolean();
                                if (b == true ){
                                    System.out.println("输入买西瓜数量");
                                    int Wnum = sc.nextInt();
                                    Booth.purchase(booths[id - 1],Wnum);
                                }
                            }
                            System.out.println("是否需要进货（true or false）");
                            boolean b = sc.nextBoolean();
                            if (b == true){
                                System.out.println("输入进货量");
                                int Wnum = sc.nextInt();
                                booths[id - 1].restock(Wnum);
                            }
                        }
                        case 2 ->{
                            Booth.closeBooths(booths);
                        }
                    }
                }
            }
            sc.close();
    }
}