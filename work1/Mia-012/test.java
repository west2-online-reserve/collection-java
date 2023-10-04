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
        Booth.purchase(booths[0],120);//向第0号摊位买120个西瓜
        Booth.closeBooths(booths);//摊位整改

    }
}
