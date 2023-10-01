public class Test {
    public static void main(String[] args) {
        Booth A = new Booth(5,"Wuhao",555,false);
        Booth B = new Booth(4,"Sihao",5555,true);
        Booth C = new Booth(3,"sanhao",55,false);
        Booth[] booths = { A,B,C };

        System.out.println(A);
        System.out.println();
        System.out.println(B);
        System.out.println();
        System.out.println(C);
        System.out.println();

        A.purchase(A,55);
        System.out.println();
        B.purchase(B,555);
        System.out.println();
        C.purchase(C,-1);
        System.out.println();


        A.restock(A,55);
        System.out.println();
        B.restock(B,555);
        System.out.println();
        C.restock(C,-1);
        System.out.println();



        Booth.closeBooths(booths);

    }
}