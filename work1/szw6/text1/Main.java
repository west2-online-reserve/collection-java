package text1;

public class Main {
    public static void main(String[] args) {
        WatermelonBooth booth = new WatermelonBooth(1, "张三", 100, false);
        System.out.println(booth.toString());

        WatermelonBooth.purchase(booth, 50);
        System.out.println(booth.toString());

        booth.restock(150);
        System.out.println(booth.toString());

        WatermelonBooth[] booths = {booth};
        WatermelonBooth.closeBooths(booths);
        System.out.println(booth.toString());
    }
}
