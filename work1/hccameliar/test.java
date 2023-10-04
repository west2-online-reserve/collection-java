package HOMEWORK;

public class test {
    public static void main(String[] args) {
        // 创建摊位对象
        WaterMelonbooth booth1 = new WaterMelonbooth(1, "小明", 100, false);
        WaterMelonbooth booth2 = new WaterMelonbooth(2, "李华", 150, false);
        WaterMelonbooth booth3 = new WaterMelonbooth(3, "大卫", 50, true);

        // 打印摊位信息
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());
        System.out.println(booth3.toString());

        // 进行购买
        booth1.purchase(50);
        booth2.purchase(200);
        booth3.purchase(10);

        // 进行进货
        booth1.restock(50);
        booth2.restock(100);
        booth3.restock(20);

        // 休业整改
        WaterMelonbooth.closeBooths(new WaterMelonbooth[]{booth1, booth2, booth3});
    }
}
//测试结果
        /*西瓜摊：{id=1, name='小明', total=100, isClosed=false}
        西瓜摊：{id=2, name='李华', total=150, isClosed=false}
        西瓜摊：{id=3, name='大卫', total=50, isClosed=true}
        购买成功：购买了50个西瓜!
        购买失败：购买西瓜数不能大于在售西瓜数!
        购买失败：商家处于休摊整改状态!
        进货了 50个西瓜!
        进货了 100个西瓜!
        进货了 20个西瓜!
        休业整改的摊位：西瓜摊：{id=3, name='大卫', total=70, isClosed=true}
*/