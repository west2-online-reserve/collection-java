// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Booth b1=new Booth(1,"a",1,false);
        Booth b2=new Booth(2,"b",3,true);
        Booth[]booths={b1,b2};
        System.out.println(b1.purchase(b1,2));
        System.out.println(b1.toString());
        Booth.closeBooths(booths);
    }
}