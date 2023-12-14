// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class BoothTest  {
    public static void main(String[] args) {
        Booth b1=new Booth(1,"a",1,false);
        Booth b2=new Booth(2,"b",3,true);
        Booth[]booths={b1,b2};
      //toString打印信息
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        //测试交易
        System.out.println(  Booth.purchase(b1,2));
        System.out.println(b1.purchase(b1,-1));
        System.out.println(b1.purchase(b2,2));
      //测试进货
        System.out.println(b1.restock(b1,220));
        System.out.println(b2.restock(b2,100));
        System.out.println(b2.restock(b2,-100));
       //测试歇业
        Booth.closeBooths(booths);
    }
}
