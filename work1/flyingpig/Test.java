public class Test {
    public static void main(String[] args) {
        // 初始化摊位
        WatermelonBooth booth1 = new WatermelonBooth(1, "张三", 100, false);
        WatermelonBooth booth2 = new WatermelonBooth(2, "李四", 50, false);

        // 展示当前摊位信息
        System.out.println("摊位信息：");
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());

        // 购买西瓜
        System.out.println("\n购买西瓜测试：");
        WatermelonBooth.purchase(booth1, 30);
        WatermelonBooth.purchase(booth2, 60); // 这次购买将失败

        // 进货
        System.out.println("\n进货测试：");
        booth1.restock(50);
        booth2.restock(300); // 这次进货将失败

        // 关闭摊位进行整改
        System.out.println("\n关闭摊位测试：");
        WatermelonBooth.closeBooths(new WatermelonBooth[]{booth1, booth2});

        // 再次展示当前摊位信息
        System.out.println("\n摊位信息：");
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());
    }
}
