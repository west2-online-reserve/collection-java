
public class Test {
    public static void main(String[] args) {
    stall booth01 = new stall(7, "老王", 100, false);
        System.out.println(booth01);
        booth01.restock(150);
        stall.purchase(booth01, 50);
        stall[] booths={booth01};
        stall.closeBooths(booths);
        System.out.println(booth01);
}
}