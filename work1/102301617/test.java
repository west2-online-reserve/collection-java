public class test {
    public static void main(String[] args) {
        //购买西瓜
        Booth booth1 = new Booth(123L, "sb", 130, false);
        booth1.purchase(booth1, 300);/
        booth1.purchase(booth1, -30);
        booth1.setIsClosed(true);
        booth1.purchase(booth1, 100);/
        booth1.setIsClosed(false);
        booth1.purchase(booth1, 20);
        System.out.println(booth1.getTotal());
        System.out.println();

        //进货
        Booth booth2 = new Booth(456L, "2b", 500, false);
        booth2.restock(booth2, 230);
        booth2.restock(booth2, -5);
        booth2.restock(booth2, 50);
        System.out.println(booth2.getTotal());
        System.out.println();

        //歇业
        Booth[] booths = {booth1, booth2};
        booth1.setIsClosed(true);
        for (int i = 0; i < booths.length; i++) {
            System.out.println("booth" + (i + 1) + " " + booths[i].getIsClosed());
        }//booth1 true  booth2 false
        booth2.closeBooths(booths);
        System.out.println(booth2.toString());
    }
}
