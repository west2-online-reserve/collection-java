public class Main {
    public static void main(String[] args) {
        Booth b = new Booth(114514, "Xiaoheizi", 19, false);
        String s = b.toString();
        System.out.print(s);
        Booth.purchase(b, 10);
        Booth.purchase(b, 10);//库存不足测试
        Booth.purchase(b, -10);//买入负数测试
        b.setClosed(true);
        Booth.purchase(b, 10);//休摊买入测试
        b.setClosed(false);
        b.restock(201);//进货超出200
        b.restock(200);
        b.restock(-10);//进货负数
        Booth.purchase(b,2000);
        System.out.println(b.toString());
        b.setClosed(true);
        Booth[] array = new Booth[2];
        array[0] = b;
        Booth b2 = new Booth(1919810, "otto", 233, false);
        array[1] = b2;
        Booth.closeBooths(array);
        Booth.closeBooths(array);
    }
}
