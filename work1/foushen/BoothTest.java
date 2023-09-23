public class BoothTest {
    public static void main(String[] args) {
        Booth myBooth = new Booth(114514,"xbz西瓜摊",0,false);
        Booth yourBooth = new Booth(22,"不知道西瓜摊",0,true);
        myBooth.restore(201);
        myBooth.restore(114);
       Booth.purchase(myBooth,200);
       Booth.purchase(myBooth,-1);
       Booth.purchase(myBooth,55);
       Booth[] booths = {myBooth,yourBooth};
       Booth.closeBooths(booths);

    }
}