public class Bonus {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        int[] arr1 = {1,3,5,7,9};
        int[] arr2 = {2,4,6,8,10};
        t1.arr = arr1;
        t2.arr = arr2;
        t1.start();
        t2.start();
    }
}
