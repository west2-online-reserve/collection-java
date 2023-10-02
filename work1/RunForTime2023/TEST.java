public class TEST {
    public static void main(String[] args) { //TEST Module
        Booth stall_1 = new Booth(101, "Genshin", 79, false);
        System.out.println(stall_1.toString());
        Booth.purchase(stall_1, 20);
        Booth.purchase(stall_1, 79);
        stall_1.setClosed(true);
        Booth.purchase(stall_1, 3);
        stall_1.setClosed(false);
        stall_1.restock(-10);
        stall_1.restock(245);
        stall_1.restock(68);
        Booth[] stall_2 = {new Booth(101, "Genshin", 79, false), new Booth(102, "LOL", 43, true)};
        Booth.closeBooths(stall_2);
        System.out.println();
        System.out.println(stall_2[0].toString());
        System.out.println(stall_2[1].toString());
    }
}