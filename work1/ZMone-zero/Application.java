public class Application {
    public static void main(String[] args) {
        Booth a1 = new Booth(56, "ZMone", 489, false);
        Booth a2 = new Booth(57, "ZMtwo", 485, true);
        Booth a3 = new Booth(58, "ZMthree", 844, false);
        Booth[] booths = {a1, a2, a3};


        a1.purchase(a1,55);

        a2.purchase(a2,5848);

        a3.purchase(a3,-50);


        a1.restock(55);

        a2.restock(555);

        a3.restock(-1);



        Booth.closeBooths(booths);

    }
}