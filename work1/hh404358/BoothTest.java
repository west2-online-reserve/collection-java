public class BoothTest {
    //test
    public static void main(String[] args) {

        Booth[] booths=new Booth[6];
        //第一种实例化
//        Booth b1=new Booth(1,"haha1",101,true);
//        Booth b2=new Booth(2,"haha2",301,true);
//        Booth b3=new Booth(3,"haha3",156,false);
//        Booth b4=new Booth(4,"haha4",121,true);
//        Booth b5=new Booth(5,"haha5",391,false);
//        Booth b6=new Booth(6,"haha6",231,true);
//        booths[0]=b1;
//        booths[1]=b2;
//        booths[2]=b3;
//        booths[3]=b4;
//        booths[4]=b5;
//        booths[5]=b6;
        //第二种：偷懒式
        for(int i=0;i<6;i++)
            booths[i]=new Booth(i+1,"haha"+i,100*i,true);


        //toString
        String s=booths[2].toString();
        System.out.println(s);
        //restock
        booths[2].restock(10);
        s=booths[2].toString();
        System.out.println(s);
        //purchase
        Booth.purchase(booths[5],9);
        s=booths[5].toString();
        System.out.println(s);

        //closeBooths
        Booth.closeBooths(booths);



    }
}