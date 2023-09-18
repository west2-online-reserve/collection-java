package XWB;

public class Main {
    public static void main(String[] args) {
        Booth a = new Booth(2202L, "蔡徐坤", 913, false);
        Booth b = new Booth(2203L, "李易峰", 2000, false);
        Booth c = new Booth(2204L, "吴亦凡", 2500, false);
        Booth d = new Booth(2205L, "李嘉琪",79,false);
        Booth e = new Booth(2206L,"邓伦",100,true);
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);

        Booth.purchase(a,100);
        Booth.purchase(b,0);
        Booth.purchase(c,-100);
        Booth.purchase(d,100);
        Booth.purchase(e,50);

        a.restock(100);
        b.restock(100);
        c.restock(250);
        d.restock(300);
        e.restock(-1);

        Booth[] booths = {a, b, c, d, e};
        Booth.closeBooths(booths);
    }
}
