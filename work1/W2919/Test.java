public class Test {
    public static void main(String[] args) {
        Booth A = new Booth(5,"Wuhao",555,false);
        Booth B = new Booth(4,"Sihao",444,true);
        Booth[] booths = { A,B };

        System.out.println(A);
        System.out.println();
        System.out.println(B);
        System.out.println();

        A.purchase(A,55);
        System.out.println();
        B.purchase(B,555);
        System.out.println();

        A.restock(A,55);
        System.out.println();
        B.restock(B,555);
        System.out.println();

        Booth.closeBooths(booths);

    }
}