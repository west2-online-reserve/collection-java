package Bonus;

public class MyThread implements Runnable {
    private int[] arr;
    private static final Object lock = new Object();
    public MyThread(int[] arr) {
        this.arr = arr;
    }
    @Override
    public void run() {
        int i=0;
        while (true) {
            synchronized (lock) {
                if (i == arr.length) {
                    lock.notifyAll();
                    break;
                }
                System.out.println(arr[i]);
                i++;
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }


        }
    }
}
