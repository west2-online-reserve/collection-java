public class Main {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1, "张三", 100, false);


        System.out.println(booth1.toString());
        Booth.purchase(booth1, 50); // 购买成功
        booth1.restock(150); // 进货成功


        Booth booth2 = new Booth(2, "李四", 200, false);
        System.out.println(booth2.toString());
        Booth.purchase(booth2, 300); // 购买失败，库存不足
        booth2.restock(250); // 进货失败，超过单次进货量

        Booth booth3 = new Booth(3, "王五", 300, true);


        System.out.println(booth3.toString());
        Booth.purchase(booth3, 250); // 购买失败。商家处于休摊整改状态
        booth1.restock(150); //


        Booth booth4 = new Booth(4, "赵六", 100, false);
        System.out.println(booth4.toString());
        Booth.purchase(booth4, -50); // 购买失败
        booth4.restock(-150); // 进货失败，不能为负数，要大于0
        Booth.closeBooths(new Booth[]{booth1, booth2,booth3,booth4});
    }
}
