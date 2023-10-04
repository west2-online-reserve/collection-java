public class main {
    public static void main(String[] args) {
        Booth a=new Booth(001,"小黑",200,true);
        Booth b=new Booth(002,"小白",300,false);
        Booth c=new Booth(003,"小苦",10,false);
        System.out.println(a.toString());
        Booth.purchase(a,456);
        Booth.purchase(a,-999);
        Booth.purchase(a,0);
        Booth.purchase(a,110);
        a.restock(201);
        a.restock(200);
        a.restock(-1);
        Booth[] booths={a,b,c};
        Booth.closeBooth(booths);
    }
}
