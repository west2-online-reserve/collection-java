package westwork2;

public class Bouns2 {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};

        interleavedOutput(arr1, arr2);
    }

    public static void interleavedOutput(int[] arr1, int[] arr2) {
        Object lock = new Object();
        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                for (int i : arr1) {
                    System.out.print(i + " ");
                    lock.notify();
                    try {
                        if (i != arr1[arr1.length - 1]) {  // Prevent the last wait to avoid deadlock
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                for (int i : arr2) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                    System.out.print(i + " ");
                    lock.notify();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
