package watermelon;

import java.util.Objects;
import java.util.Scanner;

public class Test {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Booth t2 = new Booth();
        System.out.println("请输入摊位号：");
        t2.setId(scanner.nextLong());
        t2.getId();
        System.out.println("请输入摊主姓名");
        t2.setName(scanner.next());
        t2.getName();
        System.out.println("请输入在售西瓜数");
        t2.setTotal(scanner.nextInt());
        t2.getTotal();
        System.out.println("是否休摊整改(Y/N)");
        String a = scanner.next();
        if (Objects.equals(a,"N")) {
            t2.setClosed(true);
            t2.isClosed();
        }else {
            t2.setClosed(false);
            t2.isClosed();
        }
        t2.toString();
        Scanner sc = new Scanner(System.in);
        System.out.println("请问摊主要进多少西瓜");
        int booth1 = sc.nextInt();
        Booth booth = new Booth();
        booth.restock(booth1);

        System.out.println("请问要向商家购买多少西瓜");
        int booth2 = sc.nextInt();
        t2.purchase(booth2);

        t2.closeBooth();
    }
}



