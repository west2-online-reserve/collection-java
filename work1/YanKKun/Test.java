
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

        Booth.closeBooths(new Booth[]{booth1, booth2});
    }
    }

