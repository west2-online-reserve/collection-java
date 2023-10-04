public class TestBooth {
    public static void main(String[] args) {
        Booth a=new Booth(10001,"1号",300,false);
        System.out.println(a.getId());
        System.out.println(a.getName());
        System.out.println(a.getTotal());
        System.out.println(a.getIsClosed());
        a.setId(10002);
        a.setName("0号");
        a.setTotal(299);
        a.setIsClosed(true);
        System.out.println("");
        System.out.println(a.getId());
        System.out.println(a.getName());
        System.out.println(a.getTotal());
        System.out.println(a.getIsClosed());
        System.out.println(a.toString());
        System.out.println("");
        Booth.purchase(a,100);
        a.setIsClosed(false);
        Booth.purchase(a,100);
        Booth.purchase(a,-1);
        a.setTotal(50);
        Booth.purchase(a,100);
        System.out.println("");
        a.restock(300);
        a.restock(100);
        a.restock(-1);
        System.out.println(a.getTotal());
        System.out.println("");
        Booth[] booths=new Booth[3];
        booths[0]=new Booth(1,"01号",100,false);
        booths[1]=new Booth(2,"02号",200,true);
        booths[2]=new Booth(3,"03号",300,false);
        Booth.closeBooths(booths);
        System.out.println(booths[0].getIsClosed());
        System.out.println(booths[2].getIsClosed());
    }
}
