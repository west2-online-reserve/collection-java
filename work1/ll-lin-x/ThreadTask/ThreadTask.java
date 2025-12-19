package west2.task1.ThreadTask;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadTask {
    public static void main(String[] args) {
        int[] arr1 = {1,3,5,6,9};
        int[] arr2 = {2,4,6,8,10};
        ThreadTask t1 = new ThreadTask();
        t1.printArray(arr1,arr2);
    }

    public void printArray(int[] arr1,int[] arr2){
        AtomicInteger index = new AtomicInteger(0);
        Object o1 = new Object();
        Thread t1 = new Thread(()->{
            for(int i=0;i<arr1.length;i++){
                synchronized (o1){
                    if(index.get()%2 != 0){
                        try {
                            o1.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(arr1[i]);
                    index.incrementAndGet();
                    o1.notify();
                }
            }
        });
        Thread t2 = new Thread(()->{
            for(int i=0;i<arr2.length;i++){
                synchronized (o1){
                    if(index.get()%2 != 1){
                        try {
                            o1.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println(arr2[i]);
                    index.incrementAndGet();
                    o1.notify();
                }
            }
        });
        t1.start();
        t2.start();
    }
}

