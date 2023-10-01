public class testForFail {
    public static void main(String[] args) {
        Booth s1=new Booth(101,"q1",120,false);



        Booth.purchase(s1,-20);
        Booth.purchase(s1,400);

       s1.setClosed(true);
        Booth.purchase(s1,40);

        s1.restock(30);
        s1.restock(230);





    }
}
