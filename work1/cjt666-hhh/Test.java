package hh;
public class Test {
    public static void main(String[] args) {
        Booth a=new Booth(100010,"aaa",22,true);
        Booth b=new Booth(100020,"bbb",23,false);
        Booth c=new Booth(100030,"ccc",24,false);

        Booth[] booths=new Booth[3];

        booths[0]=a;
        booths[1]=b;
        booths[2]=c;




        //测试进货restock

        //System.out.println(a.getTotal());
        //a.restock(11);
        //System.out.println(a.getTotal());
        //System.out.println(b.getTotal());
        //b.restock(11);
        //System.out.println(b.getTotal());
        //System.out.println(c.getTotal());
        //c.restock(11);
        //System.out.println(c.getTotal());



        //测试华强买瓜
        //b店没关门可以买瓜，a店关门了无法买瓜
        //System.out.println(b.getTotal());
        //b.purchase(20);
        //System.out.println(b.getTotal());
        //System.out.println(a.getTotal());
        //a.purchase(1);
        //System.out.println(a.getTotal());





    }


    }












