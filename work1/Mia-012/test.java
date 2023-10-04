import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Booth[] booths = new Booth[2];
        booths[0]=new Booth();
        booths[1]=new Booth();
            booths[0].inItl(1000,"开心", 100, false);
            booths[1].inItl(2000,"哈哈", 120, true);
        //初始化西瓜摊
        booths[0].restock(100);//第0号摊位进货100个西瓜
        booths[0].restock(300);//进货超量
        booths[0].restock(-100);//输入错误
        Booth.purchase(booths[0],120);//向第0号摊位买120个西瓜
        Booth.purchase(booths[0],-100);//购买的西瓜为负数
        Booth.purchase(booths[0],500);//购买的西瓜超过在售数量
        Booth.purchase(booths[1],50);//商家正休摊整改

        Booth.closeBooths(booths);//摊位整改
        Booth.closeBooths(booths);
    }
}
