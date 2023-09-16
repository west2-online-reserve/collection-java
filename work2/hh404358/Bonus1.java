package org.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//bonus task1
public class Bonus1{
    //bonus1:多线程输出
    private static int[] array1 = {1, 3, 5, 7, 9};
    private static int[] array2 = {2, 4, 6, 8, 10};
    private static volatile boolean flag = true;
    private static Lock lock = new ReentrantLock();
    private static Condition condition1 = lock.newCondition();
    private static Condition condition2 = lock.newCondition();

    public static class Thread1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < array1.length; i++) {
                    while (!flag) {
                        try {
                            condition1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(array1[i] + " ");
                    flag = false;
                    condition2.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static class Thread2 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < array2.length; i++) {
                    while (flag) {
                        try {
                            condition2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(array2[i] + " ");
                    flag = true;
                    condition1.signal();
                }
            } finally {
                lock.unlock();
            }
        }
    }
    public void test1() {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();

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
