package hh;
public class test {
    public static void main(String[] args) {
        Booth a=new Booth(100010,"aaa",22,true);
        Booth b=new Booth(100020,"bbb",23,false);
        Booth c=new Booth(100030,"ccc",24,false);

        Booth[] booths=new Booth[3];

        booths[0]=a;
        booths[1]=b;
        booths[2]=c;

        closeBooths(booths);

        a.restock(2);

        System.out.println(a.total);


    }
    public static void purchase(Booth b, int i) {
        if (i >= 0 && b.isClosed() == false && i <= b.getTotal()) {
            b.setTotal(b.getTotal() - i);

        } else {
            System.out.println("不符合条件，购买失败");

        }

    }

    public static void closeBooths(Booth[] booths)
    {

        for (Booth i:booths) {
            if(i.isClosed())
            {
                System.out.println(i.toString());
            }

            else{
                i.setClosed(true);
            }

        }


    }
}







