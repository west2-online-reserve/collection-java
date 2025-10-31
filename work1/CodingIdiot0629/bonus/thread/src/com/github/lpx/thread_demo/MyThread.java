package com.github.lpx.thread_demo;

public class MyThread extends Thread {
    public volatile static int turn = 0;
    private int index;
    private int need;
    private int[] arr;
    String lock;

    public MyThread(String name, int[] arr, String lock, int index, int need) {
        super(name);
        this.arr = arr;
        this.lock = lock;
        this.index = index;
        this.need = need;
    }

    @Override
    public void run() {
        while (index < arr.length) {
            synchronized (lock) {
                while (index < arr.length && turn != need) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (index < arr.length) {
                    System.out.print(arr[index] + " ");
                    turn = 1 - turn;
                    index++;
                }
                lock.notifyAll();
            }
        }
    }
}
