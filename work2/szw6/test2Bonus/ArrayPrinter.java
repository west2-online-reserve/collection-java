package szw.test2Bonus;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ArrayPrinter {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private volatile boolean printArr1 = true;

    public void printArrays(int[] arr1, int[] arr2) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                lock.lock();
                try {
                    while (!printArr1) {
                        condition.await();
                    }
                    System.out.print(arr1[i] + " ");
                    System.out.flush();
                    printArr1 = false;
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < arr2.length; i++) {
                lock.lock();
                try {
                    while (printArr1) {
                        condition.await();
                    }
                    System.out.print(arr2[i] + " ");
                    System.out.flush();
                    printArr1 = true;
                    condition.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
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

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};

        ArrayPrinter printer = new ArrayPrinter();
        printer.printArrays(arr1, arr2);
    }
}

