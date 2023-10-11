public class BoothTest {
    public static void main(String[] args) {
        WatermelonBooth booth = new WatermelonBooth(1,"John",130,false);
        WatermelonBooth booth1 = new WatermelonBooth(2,"Tom",130,true);
        //购买成功测试
        System.out.println(booth.purchase(booth,100));
        //购买失败测试
        System.out.println(booth.purchase(booth,250));
        System.out.println(booth.purchase(booth,150));
        //进货成功测试
        System.out.println(booth.restock(50));
        //进货失败测试
        System.out.println(booth.restock(250));
        //停业测试
        WatermelonBooth[] booths =new WatermelonBooth[2];
        booths[0]=booth;
        booths[1]=booth1;
        booth.closeBooths(booths);
        System.out.println(booths[0].toString());//是否变为休业
    }
}
