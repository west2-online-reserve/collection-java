package Watermelon;

public class Example {

        public static void main(String[] args) {
            // 创建摊位对象
            Booth booth1 = new Booth(1, "张三", 100, false);
            Booth booth2 = new Booth(2, "李四", 50, false);
            Booth booth3 = new Booth(34, "王五", 10000, true);
            Booth booth4 = new Booth(878, "赵六", 30, false);
            Booth booth5 = new Booth(9999, "耿七", 204, true);
            Booth[] booths={booth1,booth2,booth3,booth4,booth5};


            // 输出摊位信息
            for (Booth b:booths){
                System.out.println(b);
            }

            // 购买西瓜
            Booth.purchase(booth1, 20);
            Booth.purchase(booth2, 80);
            Booth.purchase(booth3, 80);
            Booth.purchase(booth1, -20);

            // 进货
            booth1.restock(50);
            booth2.restock(300);
            booth3.restock(70);
            booth1.restock(-50);

            // 关闭摊位
            Booth.closeBooths(new Booth[]{booth1, booth2,booth3,booth4,booth5});

            // 输出摊位信息
            for (Booth b:booths){
                System.out.println(b);
            }

}
