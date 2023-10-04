package work1;

public class BoothTest {
    public static  void main(String[] args) {
        Booth boothsA = new Booth(1,"a",500,false);
        Booth boothsB = new Booth(2,"b",1000,true);
        Booth boothsD = new Booth(4,"d",-100,true);
        Booth boothsC = new Booth();
        boothsC.setId(3);
        boothsC.setName("c");
        boothsC.setTotal(1500);
        boothsC.setIsClosed(true);
        System.out.println(boothsC.toString());
        System.out.println(boothsA.toString());
        System.out.println(boothsB.toString());
        System.out.println("");

        Booth[]booths = new Booth[2];
        booths[0] = boothsA;
        booths[1] = boothsB;
        Booth.purchase(boothsA,20);
        Booth.purchase(boothsB,1100);
        Booth.purchase(boothsD,20);
        System.out.println("");

        boothsB.restock(300);
        boothsB.restock(-100);
        boothsB.restock(100);
        System.out.println("");

        Booth.closeBooths(booths);
    }
}
