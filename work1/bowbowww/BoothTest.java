package work1;

public class BoothTest {
    public static  void main(String[] args) {
        Booth boothsA = new Booth(1,"a",500,false);
        Booth boothsB = new Booth(2,"b",1000,true);
        Booth[]booths = new Booth[2];
        booths[0] = boothsA;
        booths[1] = boothsB;
        System.out.println(boothsA.toString());
        Booth.purchase(boothsA,20);
        boothsB.restock(300);
        Booth.closeBooths(booths);
    }
}
