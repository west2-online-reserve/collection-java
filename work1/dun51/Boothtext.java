package demo1;

public class Boothtext {
    public static void main (String[] args){
        Booth booth1 = new Booth();
        booth1.Booth(123L, "姜姜", 150, false);
        booth1.purchase(booth1, 300);//购买失败，该摊在售西瓜数不足
        booth1.purchase(booth1, -30);//购买失败，购买数量错误
        booth1.setClosed(true);
        booth1.purchase(booth1, 100);//购买失败，该摊已休摊
        booth1.setClosed(false);
        booth1.purchase(booth1, 20);//购买成功
        System.out.println(booth1.getTotal());//130
        System.out.println();

        //进货
        Booth booth2 = new Booth();
        booth2.Booth(456L, "小菊", 520, false);
        booth2.restock(booth2, 230);//进货失败，单次进货量不能超过200
        booth2.restock(booth2, -5);//进货失败，单次进货量不能少于0
        booth2.restock(booth2, 50);//进货成功
        System.out.println(booth2.getTotal());//570
        System.out.println();

        //歇业
        Booth[] booths = {booth1, booth2};
        booth1.setClosed(true);
        for (int i = 0; i < booths.length; i++) {
            System.out.println("booth" + (i + 1) + " " + booths[i].isClosed());
        }//booth1 true  booth2 false
        booth2.closeBooths(booths);//booth1是true,所以直接打印出相关信息;booth2是false,所以被转换为true
        System.out.println(booth2.toString());//打印booth2相关信息,此时booth2为true
    }
}
