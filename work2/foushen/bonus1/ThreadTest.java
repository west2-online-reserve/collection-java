package bonus1;


public class ThreadTest {
    static public void printTwoArrays(int[] lhs,int[] rhs){
        Thread1 thread1 = new Thread1(lhs);
        Thread1 thread2 = new Thread1(rhs);
        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        int[] lhs = {1,3,5,7,9};
        int[] rhs = {2,4,6,8,10};
        printTwoArrays(lhs,rhs);
    }
}
