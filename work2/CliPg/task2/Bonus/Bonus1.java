package com.PeanutJava.task2.Bonus1;

import com.PeanutJava.Thread.Desk;

public class Bonus1 {

    private static int[] array1 = {1, 3, 5, 7, 9};
    private static int[] array2 = {2, 4, 6, 8, 10};
    public static Object lock = new Object();
    private static volatile int flag = 1;


    public static void main(String[] args) {
        ArrayPrinter();
    }


    public static class Thread1 extends Thread {
        int i =0;
        @Override
        public void run() {
            while (true){
                synchronized (lock){
                    if(array1.length==i){
                        break;
                    }else{
                        if(flag ==0){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }else{
                            System.out.println(array1[i]);
                            i++;
                            flag =0;
                            lock.notifyAll();
                        }
                    }
                }

            }

        }
    }

    public static class Thread2 extends Thread {
        int i =0;
        @Override
        public void run() {
            while (true){
                synchronized (lock){
                    if(array2.length==i){
                        break;
                    }else{
                        if(flag==1){
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }else{
                            System.out.println(array2[i]);
                            i++;
                            flag =1;
                            lock.notifyAll();
                        }
                    }
                }

            }

        }
    }

    public static void ArrayPrinter(){

        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();

        t1.start();
        t2.start();


    }



}
