public class TEST {

    public static void main(String[] args) {

        // 创建摊位
        Booth booth1 = new Booth(1, "张三", 100, false);
        Booth booth2 = new Booth(2, "李四", 80,  false);

        //打印摊位信息
        System.out.println(booth1);
        System.out.println(booth2);

        // 正常购买
        Booth.purchase(booth1, 50);

        // 购买数量为0
        Booth.purchase(booth1, 0);

        // 购买数量超过在售数量
        Booth.purchase(booth2, 120);

        // 摊位已休业
        booth1.setClosed(true);
        Booth.purchase(booth1, 10);

        // 进货正常
        booth1.setClosed(false);
        booth1.restock(100);

        // 进货超过200
        booth1.restock(300);

        //进货量为负数
        booth2.restock(-50);

        // 关闭摊位
        Booth[] booths = {booth1, booth2};
        Booth.closeBooths(booths);

    }

}