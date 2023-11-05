public class Bonus1 {

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};

        interleavedOutput(arr1, arr2);
    }
    // 使用synchronized,使得多线程访问按照一定顺序
    public static void interleavedOutput(int[] arr1, int[] arr2) {
        Object lock = new Object();
        // lock 被用作同步锁，确保在同一时刻只有一个线程能够进入同步块。

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                for (int i : arr1) {
                    System.out.print(i + " ");
                    try {
                        lock.notify(); // 唤醒等待的线程
                        lock.wait();   // 当前线程等待
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify(); // 唤醒可能还在等待的线程
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                for (int i : arr2) {
                    System.out.print(i + " ");
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.notify();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}