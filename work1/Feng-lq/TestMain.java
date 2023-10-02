package work1;

import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        Booth booth1= new Booth(1001,"张三",500,true);
        Booth booth2= new Booth(1002,"李四",800,false);
        Booth booth3= new Booth(1003,"王五",900,true);
        Booth booth4= new Booth(1004,"马六",100,false);
        Booth booth5= new Booth(1005,"小明",50,true);
        Booth booth6= new Booth(1006,"小光",500,false);
        Booth[] booths = {booth1,booth2,booth3,booth4,booth5,booth6};

        Operation restock = new Operation();

        Operation.purchase(booth1,400);
        Operation.purchase(booth6,600);
        Operation.purchase(booth2,400);
        Operation.purchase(booth4,-90);

        restock.restock(booth3,-10);
        restock.restock(booth5,200);

        Operation.closeBooths(booths);





    }
}
