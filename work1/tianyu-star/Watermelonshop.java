import java.util.*;
public class Watermelonshop{
    public static void main(String[] args){
        Booth watermelon=new Booth(9527,"xqx",0,false);

        Scanner scanner=new Scanner(System.in);
        System.out.print("please type the total:");
        int t=scanner.nextInt();
        System.out.print("please type the isClosed:");
        boolean i=scanner.nextBoolean();
        System.out.print("please type the buying:");
        int b=scanner.nextInt();
        System.out.printf(Booth.purchase(watermelon,b));
        System.out.print("please type the getting:");
        int g=scanner.nextInt();
        System.out.printf(watermelon.restock(g));
        watermelon.setTotal(t);
        watermelon.setIsClosed(i);
        Booth.purchase(watermelon,b);
        watermelon.restock(g);
        System.out.printf(watermelon.toString());


    }
}





