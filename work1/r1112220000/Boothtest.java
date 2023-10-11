public class Boothtest {
    public static void main(String[] args) {
        // 创建Booth对象
        Booth booth1 = new Booth(1, "1", 100, false);
        Booth booth2 = new Booth(2, "2", 150, false);

        // 测试购买功能
        System.out.println("测试购买功能：");
        Booth.purchase(booth1, 50); // 正常购买
        Booth.purchase(booth1, -1); // 购买数量小于等于0
        Booth.purchase(booth1, 200); // 购买数量大于剩余西瓜数量

        // 测试进货功能
        System.out.println("\n测试进货功能：");
        Booth.restock(50); // 正常进货
        Booth.restock(-10); // 进货数量为负数
        Booth.restock(300); // 进货数量大于200

        // 测试关闭摊位功能
        System.out.println("\n测试关闭摊位功能：");
        Booth.closeBooths(new Booth[]{booth1, booth2}); // 未关闭摊位
        Booth.closeBooths(new Booth[]{booth1}); // 关闭单个摊位
    }
}
