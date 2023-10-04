public class TEST {

    public static void main(String[] args) {

        // 创建摊位
        Booth booth1 = new Booth(1, "张三", 100, false);
        Booth booth2 = new Booth(2, "李四", 80, false);

        // 购买西瓜
        booth1.purchase(50);

        // 进货
        booth1.restock(120);

        // 关闭摊位
        Booth[] booths = {booth1, booth2};
        Booth.closeBooths(booths);

    }

}