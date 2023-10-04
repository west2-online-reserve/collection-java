
import  Booths.Booth;

public class Test {
    public static void main(String[] args) {
          /*歇业方法测试，成功输出xiaohong的摊位信息，
          * 并且将其余的摊位关闭
          Booth[] booth=new Booth[3];
          booth[0]=new Booth(12343546,"xiaoming",160,false);
          booth[1]=new Booth(12243546,"xiaohong",180,true);
          booth[2]=new Booth(12343986,"xiaogang",200,false);
          Booth.closeBooths(booth);*/
          /*购买方法测试1,成功购买西瓜并减少了在售西瓜数
         Booth booth=new Booth(12324345,"xiaoliang",280,false);
         Booth.purchase(booth,203);
         System.out.println(booth.toString());*/
          /*购买测试2，购买超量西瓜，输出无法交易
          Booth booth=new Booth(12324345,"xiaoliang",280,false);
          Booth.purchase(booth,290);
          System.out.println(booth.toString());*/
          /*购买测试3，购买数为负数，交易失败
          Booth booth=new Booth(12324345,"xiaoliang",280,false);
          Booth.purchase(booth,-1);
          System.out.println(booth.toString());*/
          /*购买测试4，休摊情况，交易失败
          Booth booth=new Booth(12324345,"xiaoliang",280,true);
          Booth.purchase(booth,203);
          System.out.println(booth.toString());*/
          /*进货测试1，进货成功，成功增加在售西瓜数
          Booth booth=new Booth(12324345,"xiaoliang",280,false);
          Booth.restock(booth,18);
          System.out.println(booth.toString());*/
          /*进货测试2，进货数为负数，无法进货
          Booth booth=new Booth(12324345,"xiaoliang",280,false);
          Booth.restock(booth,-1);
          System.out.println(booth.toString());*/
          /*进货测试3，进货数大于200，无法进货
          Booth booth=new Booth(12324345,"xiaoliang",280,false);
          Booth.restock(booth,280);
          System.out.println(booth.toString());*/

}}