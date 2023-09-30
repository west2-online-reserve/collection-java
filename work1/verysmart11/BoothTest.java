public class BoothTest {
    public static void main(String[] args) {
        Booth s1=new Booth(101,"q1",120,false);
        Booth s2=new Booth(202,"q2",100,true);
        Booth s3=new Booth(303,"q3",20,false);
        int a=20;

        Booth.purchase(s1,a);

        System.out.println(s1.toString());
        System.out.println(s2.toString());
        System.out.println(s3.toString());

        s3.restock(30);
        s3.restock(230);

        Booth[] booths=new Booth[3];
        booths[0]=s1;
        booths[1]=s2;
        booths[2]=s3;


        Booth.closeBooth(booths);


        int x = 10;
        x = x * 5;
        System.out.print(x);





    }
}
