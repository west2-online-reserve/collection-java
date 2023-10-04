public class BoothTest {
    public static void main(String[] args) {
        // 创建一些 Booth 实例用于测试
        Booth booth1 = new Booth(1, "摊主1", 50, false);
        Booth booth2 = new Booth(2, "摊主2", 100, true);

        // 测试 purchase() 方法
        Booth.purchase(booth1, 10); // 购买成功
        Booth.purchase(booth1, -5); // 购买失败，请重新购买（因为数量为负数）
        Booth.purchase(booth1, 60); // 购买失败，请重新购买（因为数量大于在售西瓜数）
        Booth.purchase(booth2, 20); // 购买失败，请重新购买（因为摊位休摊整改）
        System.out.printf("");
        // 测试 restock() 方法
        booth1.restock(50); // 进货成功
        booth1.restock(0);  // 进货失败，请重新进货（因为数量为零）
        booth1.restock(201); // 进货失败，请重新进货（因为数量超过200）
        System.out.println();
        // 测试 closeBooths() 方法
        Booth[] booths = {booth1, booth2};
        booth1.closeBooths(booths); // 将 booth2 标记为休摊整改
    }
}
