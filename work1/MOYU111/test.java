public class test {
    public static void main(String[] args) {
        tan a=new tan(10001,"1号",300,false);
        System.out.println(a.getId());
        System.out.println(a.getName());
        System.out.println(a.getTotal());
        System.out.println(a.getIsClosed());
        System.out.println();

        a.setId(10002);
        a.setName("2号");
        a.setTotal(400);
        a.setIsClosed(true);


        System.out.println(a.getId());
        System.out.println(a.getName());
        System.out.println(a.getTotal());
        System.out.println(a.getIsClosed());
        System.out.println();

        System.out.println(a);
        System.out.println();

        tan.purchase(a,100);
        tan.purchase(a,-1);
        a.setIsClosed(false);
        tan.purchase(a,100);
        tan.purchase(a,-1);
        a.setTotal(50);
        tan.purchase(a,100);
        System.out.println();

        a.restock(300);
        a.restock(100);
        a.restock(-1);
        System.out.println(a.getTotal());
        a.restock(100);
        System.out.println(a.getTotal());
        System.out.println();

        tan[] tans=new tan[3];
        tans[0]=new tan(1,"01号",100,false);
        tans[1]=new tan(2,"02号",200,true);
        tans[2]=new tan(3,"03号",300,false);
        tan.closetans(tans);
        System.out.println(tans[0].getIsClosed());
        System.out.println(tans[2].getIsClosed());
    }
}
