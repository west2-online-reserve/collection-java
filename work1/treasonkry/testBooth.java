package Homework;

public class testBooth {

    public static void main(String[] args) {
        //toString的测试和get/set的测试

        Booth booth = new Booth();
        booth.setId(1);
        booth.setName("a");
        booth.setTotal(300);
        booth.setClosed(false);
        Booth booth1 = new Booth();
        booth1.setId(1);
        booth1.setName("b");
        booth1.setTotal(300);
        booth1.setClosed(true);
        System.out.println(booth.toString());
        //购买方法的测试
        booth.setTotal(Booth.purchase(booth,1));
        booth.setTotal(Booth.purchase(booth,400));
        //进货测试
        booth.setTotal(booth.stock(10));
        booth.setTotal(booth.stock(201));
        //歇业
        Booth[] booths ={booth,booth1};
        Booth.closeBooth(booths);




    }
}
