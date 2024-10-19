public class Test {
    public static void main(String[] args) {
        int[] arr1={1,3,5,7,9};
        int[] arr2={2,4,6,8,10};
        ThreadToPrint t1=new ThreadToPrint(arr1);
        ThreadToPrint t2=new ThreadToPrint(arr2);
        t1.setName("Thread 1");
        t2.setName("Thread 2");
        t1.start();
        t2.start();
    }
}
