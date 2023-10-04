//package work1.iridescenline;

public class boothTest {
    public static void main(String[] args) {
        booth boothA = new booth();
        booth boothB = new booth();
        boothA.setId(123);
        boothA.setName("min");
        boothA.setTotal(30);
        boothA.setIsClosed(true);
        boothB.setId(456);
        boothB.setName("bin");
        boothB.setTotal(60);
        boothB.setIsClosed(false);
        System.out.println(boothA.toString());
        System.out.println(boothB.toString());
        boothA.purchase(20, 30, true);
        boothB.purchase(25, 60, false);
        boothA.restock(40);
        boothB.restock(40);
        booth booths[] = { boothA, boothB };
        booth.closeBooths(booths);
    }

}
