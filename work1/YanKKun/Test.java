
public class Test {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1, "小明", 100, false);
        System.out.println(booth1.toString());
        Booth.purchase(booth1, 50); // 购买成功!
        booth1.restock(150); // 进货成功


        Booth booth2 = new Booth(2, "小红", 200, false);
        System.out.println(booth2.toString());
        Booth.purchase(booth2, 400); // 购买西瓜数不能大于在售西瓜数
        booth2.restock(300); // 单次进货量不能大于200

        Booth booth3 = new Booth(3, "小1", 30, false);
        System.out.println(booth3.toString());
        Booth.purchase(booth3, -30); // 购买西瓜数需为正数
        booth3.restock(-150); // 单次进货量不能小于0

        Booth booth4 = new Booth(4, "小2", 30, true);
        System.out.println(booth4.toString());
        Booth.purchase(booth4, 50); // 商家处于休摊整改状态
        booth4.restock(150); // 进货成功


        Booth.closeBooths(new Booth[]{booth1, booth2,booth3,booth4});
        //Booth{id=4, name='小2', total=180, isClosed=true}
        System.out.println(booth1.toString());
        //Booth{id=1, name='小明', total=200, isClosed=true}
    }
    }

