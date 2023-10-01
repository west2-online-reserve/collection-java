package Watermelon;

public class Example {

        public static void main(String[] args) {
            // 创建摊位对象
            Booth booth1 = new Booth(1, "张三", 100, false);
            Booth booth2 = new Booth(2, "李四", 50, false);

            // 输出摊位信息
            System.out.println(booth1.toString());
            System.out.println(booth2.toString());

            // 购买西瓜
            Booth.purchase(booth1, 20);
            Booth.purchase(booth2, 80);

            // 进货
            booth1.restock(50);
            booth2.restock(300);

            // 关闭摊位
            Booth.closeBooths(new Booth[]{booth1, booth2});

    }

}
