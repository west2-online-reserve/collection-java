package com.sty.bonus;

public class Demo_Thread {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};

        function(arr1,arr2);
    }

    public static void function(int[] arr1, int[] arr2){
        int len = arr1.length+arr2.length;
        int[] result = new int[len];

        Thread thread1 = new Thread(() -> {
            int i = 0;
            for (int num : arr1) {
                synchronized (result) {    //给同步对象加锁
                    result[i] = num;
                    i += 2;
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            int i = 1;
            for (int num : arr2) {
                synchronized (result) {
                    result[i] = num;
                    i += 2;
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();//保证两个线程都结束了以后再输出数组
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 输出交替合并后的数组
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}
