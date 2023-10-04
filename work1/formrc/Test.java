package java_work;

public class Test {
    public static void main(String[] args) {
        Booth booth1 = new Booth(1, "zhangsan", 100, false);
        Booth booth2 = new Booth(2, "fubing", 200,true);

        System.out.println("摊位信息：");
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());


        Booth.purchase(booth1,50);
        Booth.purchase(booth1,-10);
        Booth.purchase(booth1, 150);
        Booth.purchase(booth2, 250);

        booth1.restock(-20);
        booth1.restock(250);
        booth1.restock(50);

        Booth[] booths = {booth1, booth2};
        Booth.closeBooths(booths);
    }

}
