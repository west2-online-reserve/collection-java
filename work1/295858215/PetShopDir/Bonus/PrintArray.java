package Bonus;

public class PrintArray {
    public static void main(String[] args) {
        int[] arr1={1,3,5,7,9};
        int[] arr2={2,4,6,8,10};
        MyThread myThread1=new MyThread(arr1);
        MyThread myThread2=new MyThread(arr2);
        Thread t1=new Thread(myThread1);
        Thread t2=new Thread(myThread2);
        t1.start();
        t2.start();

    }
}
