import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Booth[] b = new Booth[5];
        b[0] = new Booth(114514,"华强",100,false);
        b[1] = new Booth(20232023,"张三",70,true);
        b[2] = new Booth(1919810,"胡桃",43,false);
        b[3] = new Booth(123789,"雪王",9999,false);
        b[4] = new Booth(66668888,"赵山河",1,false);
        System.out.println("运行开始，先输出一次结果:");
        for(int i=0;i<5;i++){
            System.out.println(b[i].toString());
        }
        System.out.println("开始买瓜:");
        Booth.purchase(b[0],20);
        b[0].toString();
        Booth.purchase(b[1],20);
        b[1].toString();
        Booth.purchase(b[2],-10);
        b[2].toString();
        Booth.purchase(b[3],0);
        b[4].toString();
        Booth.purchase(b[4],2);
        b[4].toString();
        System.out.println("买完瓜了，开始休摊:");
        Booth.closeBooths(b);
        System.out.println("运行完毕，再输出一次结果:");
        for(int i=0;i<5;i++){
            System.out.println(b[i].toString());
        }
    }
}