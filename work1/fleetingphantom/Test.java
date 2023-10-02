public class Test {
    public static void main(String[] args) {
        Booth booth1 = new Booth(114,"张三",514,false);
        Booth booth2 = new Booth(191,"李四",810,true);
        //购买西瓜.
        System.out.println("\n买瓜");
        Booth.purchase(booth2,1145);
        Booth.purchase(booth1,1145);
        Booth.purchase(booth1,-1145);
        Booth.purchase(booth1,114);
        //进货.
        System.out.println("\n进货");
        booth2.restock(1919);
        booth2.restock(114);
        booth2.restock(-810);
        //歇业.
        System.out.println("\n歇业");
        Booth[] booths ={booth1,booth2};
        Booth.closeBooths(booths);
    }
}
