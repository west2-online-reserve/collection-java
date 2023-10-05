public class Test {
    public static void main(String[] args) {
        Booth[] booths = new Booth[10];
        booths[0] = new Booth(1001,"小明",200,false);
        booths[1] = new Booth(1002,"小红",100,true);
        booths[2] = new Booth(1003,"小王",150,false);
        System.out.println(booths[0].toString());
        System.out.println(booths[1].toString());
        System.out.println(booths[2].toString());

        Booth d = new Booth();
        d.setId(1004);
        d.setName("小绿");
        d.setTotal(250);
        d.setIsClosed(true);
        System.out.println(d.toString());

        Booth.purchase(booths[0],201);
        Booth.purchase(booths[0],190);
        booths[0].restock(201);

        Booth.closeBooths(booths);
    }
}
