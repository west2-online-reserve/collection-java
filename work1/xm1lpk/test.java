
public class Example {

    public static void main(String[] args) {
        Booth booth1 = new Booth(12, "张", 1, false);
        Booth booth2 = new Booth(21, "李", 50000, Sfalse);
        Booth booth3 = new Booth(3, "王", 10, true);
        Booth[] booths={booth1,booth2,booth3};
        for (Booth b:booths)
        {
            System.out.println(b);
        }
        Booth.purchase(booth1, 20);
        Booth.purchase(booth2, 80);
        Booth.purchase(booth3, -20);
        booth1.restock(50);
        booth2.restock(300);
        booth3.restock(-70);
        Booth.closeBooths(new Booth[]{booth1, booth2,booth3});
        for (Booth b:booths){
            System.out.println(b);
        }
    }
}
