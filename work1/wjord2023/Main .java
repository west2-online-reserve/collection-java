public class Main {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1, "xiaohon", 100, false);
        Booth booth2 = new Booth(2, "xiaoming", 200, true);
        Booth booth3 = new Booth(3,"xiaofang",300,true);
        System.out.println(booth1.toString()+"\n");
        System.out.println(booth2.toString()+"\n");
        System.out.println(booth3.toString()+"\n");
        Booth[] booth = {booth1, booth2,booth3};
        //为西瓜摊商家赋值
        Booth.purchase(booth1,50);
        Booth.purchase(booth2,-1);
        Booth.purchase(booth3,500);
        booth1.restock(booth1,120);
        booth2.restock(booth2,-1);
        booth3.restock(booth3,300);
        Booth.closeBooths(booth);
        //测试
    }
}
