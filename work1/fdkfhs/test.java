public class test {
    public static void main(String[] args) {
        //购买西瓜方法测试
        Booth booth1 = new Booth(123456, "张三", 300, false);
        booth1.purchase(booth1,310);  //购买失败，西瓜摊在售西瓜数不足
        booth1.purchase(booth1, -1); //购买失败，购买数量错误
        booth1.setIsClosed(true);
        booth1.purchase(booth1, 100); // 购买失败，该摊已休摊
        booth1.setIsClosed(false);
        booth1.purchase(booth1, 100); //购买成功
        System.out.println(booth1.getTotal());  //200

        System.out.println("----------------------");  //分割线，便于查看（个人习惯）

        //进货测试
        Booth booth2 = new Booth(123457,"李四", 500, false);
        booth2.restock(booth2, 500); // 进货失败,数量超过上限
        booth2.restock(booth2, -100); //进货数量不能小于0，进货失败
        booth2.restock(booth2, 100); // 进货成功
        System.out.println(booth2.getTotal()); //600

        System.out.println("-------------------");

        //歇业测试
        Booth booths[] = {booth1,booth2};
        booths[0].setIsClosed(true);
        for (int i = 0; i < booths.length; i++) {
            System.out.println("booth"+ (i + 1) + " " + booths[i].getIsClosed());
            //booth1 为true
            //booth2为false

        }


    }
}
