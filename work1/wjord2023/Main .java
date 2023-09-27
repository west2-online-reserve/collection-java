import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1, "xiaohon", 100, false);
        Booth booth2 = new Booth(2, "xiaoming", 200, true);
        Booth[] booth = {booth1, booth2};
        //为西瓜摊商家赋值
        Booth.purchase(booth1,50);
        booth1.restock(booth1,120);
        Booth.closeBooths(booth);
        //测试
    }
}
