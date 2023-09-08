public class Test {
    public static void main(String[] args) {
        Booth b1 = new Booth(1,"David", 200, true);
        Booth b2 = new Booth(34,"Dav", 2002, true);
        Booth b3 = new Booth(90,"Daid", 2300, false);
        Booth b4 = new Booth(121,"avid", 2040, true);
        Booth b5 = new Booth(291,"aavid", 2800, false);
        Booth b6 = new Booth(321,"vid", 2002, true);
        Booth[] booths = {b1, b2, b3, b4, b5, b6};

        System.out.println(b1);
        System.out.println("-----------------------------------");
        b1.purchase(300);
        b3.purchase(300);
        b1.purchase(-300);
        b2.purchase(300);
        System.out.println("-----------------------------------");
        b3.restock(30);
        b3.restock(-30);
        b3.restock(2000);
        System.out.println("-----------------------------------");
        for (Booth booth : booths) {
            booth.closeBooths(booth);
            System.out.println(booth);
        }
    }
}

