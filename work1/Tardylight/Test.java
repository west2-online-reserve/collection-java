package Wangqiangstore;
import java.util.Scanner;
public class Test {
    public static void main(String[] args) {
         //创建实例对象
        Booth A=new Booth(01,"张三",100,false);
        A.setId(01);
        A.setName("张三");
        A.setTotal(100);
        A.setIsclosed(false);
        Booth B=new Booth(02,"李四",200,true);
        B.setId(02);
        B.setName("李四");
        B.setTotal(200);
        B.setIsclosed(false);
        Booth C=new Booth(03,"王五",400,false);
        C.setId(03);
        C.setName("王五");
        C.setTotal(400);
        C.setIsclosed(true);
//        测试purchase方法
       Booth.purchase(A,-10);
       Booth.purchase(B,500);
       Booth.purchase(C,12);
       //测试进货
       A.restock(120);
       B.restock(52);
       C.restock(500);
//          测试closeBooths方法
       Booth booths[]={A,B,C};
       Booth.closeBooths(booths);
            }
}
