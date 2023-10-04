public class BoothTest {
    public static void main(String[] args) {
        Booth b1=new Booth(1,"a",1,false);
        Booth b2=new Booth(2,"b",3,true);
        Booth[]booths={b1,b2};
        System.out.println(b1.toString());
        System.out.println(b2.toString());
        System.out.println(b1.purchase(b1,2));
        System.out.println(b1.purchase(b1,-1));
        System.out.println(b1.purchase(b2,2));
        System.out.println(b1.restock(b1,220));
        System.out.println(b2.restock(b2,100));
        System.out.println(b2.restock(b2,-100));
        Booth.closeBooths(booths);
    }
}
