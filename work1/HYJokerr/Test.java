public class Test {
    public static void main(String[] args) {
        System.out.println("测试get和set方法:");
        Booth b1 = new Booth(1,"ZhangSan",10,false);
        System.out.println(b1.getId());
        System.out.println(b1.getName());
        System.out.println(b1.getTota());
        System.out.println(b1.getIsClosed());
        System.out.println(b1.toString());

        b1.setId(2);
        b1.setName("LaoWang");
        b1.setTota(11);
        b1.setIsClosed(true);
        System.out.println(b1.toString());

        System.out.println("测试purchase方法:");
        Booth b2=new Booth(3,"PURCHASE",10,false);
        Booth.purchase(b2,-1);
        Booth.purchase(b2,11);
        Booth.purchase(b1,10);
        Booth.purchase(b2,10);

        System.out.println("\n测试restock方法:");
        b2.restock(201);
        b2.restock(-1);
        b2.restock(200);

        System.out.println("\n测试closeBooths方法:");
        Booth [] booth=new Booth[2];
        booth[0]=b1;
        booth[1]=b2;
        Booth.closeBooths(booth);

    }
}
