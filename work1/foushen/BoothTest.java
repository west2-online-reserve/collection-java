import java.util.ArrayList;
import java.util.Objects;

public class BoothTest {
    public static void main(String[] args) {
        Booth myBooth = new Booth(114514, "xbz西瓜摊", 0, false);
        Booth yourBooth = new Booth(22, "不知道西瓜摊", 0, true);
        Booth itBooth = new Booth(7355608, "测试西瓜摊", 0, false);
        //测试retore函数
        myBooth.restore(20);
        yourBooth.restore(20);
        myBooth.restore(300);
        System.out.println("------------------------------------");
        //测试puchase函数
        Booth.purchase(myBooth, 10);
        Booth.purchase(myBooth, 20);//购买数量大于库存时
        Booth.purchase(myBooth, -1);
        Booth.purchase(yourBooth, 2);
        System.out.println("------------------------------------");
        //测试closeBooths函数
        Booth[] booths = {myBooth, yourBooth, itBooth};
        Booth.closeBooths(booths);


    }
}