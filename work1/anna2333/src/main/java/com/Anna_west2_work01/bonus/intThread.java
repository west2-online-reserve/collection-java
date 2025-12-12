package com.Anna_west2_work01.bonus;

import java.util.Scanner;

public class intThread {
    // 控制线程交替进行的锁
    private static final Object lock = new Object();
    // 标记当前是哪个数组
    private static int turn = 0;
    private static int index1 = 0;
    private static int index2 = 0;
    public static void main(String[] args) {
//        int[] arr1 = {1,3,5,7,9};
//        int[] arr2 = {2,4,6,8,10};
        int count = 5;
        int[] arr1 = new int[count];
        int[] arr2 = new int[count];
        Scanner sc = new Scanner(System.in);
        System.out.println("输入数组1的元素：");
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = sc.nextInt();
        }
        System.out.println("输入数组2的元素：");
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = sc.nextInt();
        }
        sc.close();
        // 控制线程交替进行的锁

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    while (index1 < arr1.length){
                        if(turn != 0){
                            // 不是该线程输出时
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.print(arr1[index1++] + " ");
                        turn = 1;
                        lock.notifyAll();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    while (index2 < arr2.length){
                        if (turn != 1){
                            // 不是该线程输出
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.print(arr2[index2++] + " ");
                        turn = 0;
                        lock.notifyAll();
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
}
