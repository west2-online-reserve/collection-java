public class test {
    public static void main(String[] args) {
        Booth booth1 =new Booth(1, "张三", 100, false);
        Booth booth2 =new Booth(2, "李四", 50, false);
        System.out.println("初始状态：");
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());
        Booth.purchase(booth1, 30);
        Booth.purchase(booth2, 60);
        System.out.println("购买后状态：");
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());
        booth1.restock(150);
        booth2.restock(220);
        System.out.println("进货后状态：");
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());
        Booth[] booths = {booth1, booth2};

        Booth.closeBooths(booths);
        System.out.println("休业整改后状态：");
        System.out.println(booth1.toString());
        System.out.println(booth2.toString());
    }
    }