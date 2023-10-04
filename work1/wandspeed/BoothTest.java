public class BoothTest {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1, "Tom", 50, false);
        Booth booth2 = new Booth(2, "李华", 100, true);

        System.out.println("摊位1信息：");
        System.out.println(booth1.toString());

        System.out.println("摊位2信息：");
        System.out.println(booth2.toString());

        Booth.purchase(booth1, 20);
        Booth.purchase(booth2, 180);
        Booth.purchase(booth2, 80);

        booth1.restock(50);
        booth2.restock(250);

        Booth[] booths = {booth1, booth2};
        Booth.closeBooths(booths);
    }
}
