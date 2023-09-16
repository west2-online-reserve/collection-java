package XWB;

public class Main {
    public static void main(String[] args) {
        Booth a = new Booth(2202L, "蔡徐坤", 913, false);
        Booth b = new Booth(2203L, "李易峰", 2000, false);
        Booth c = new Booth(2204L, "吴亦凡", 2500, true);
        System.out.println(a);
        Booth.purchase(a, 100);
        b.restock(100);
        Booth[] booths = {a, b, c};
        Booth.closeBooths(booths);
    }
}
