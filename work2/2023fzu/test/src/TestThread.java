/**
 * @author HarveyBlocks
 * @date 2023/08/27 18:48
 **/
//这个多线程的作业时假的,多线程还没有学完,只学了一点点
/*
public class TestThread {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        MyThread myThread1 = new MyThread(arr1);
        MyThread myThread2 = new MyThread(arr2);
        new Thread(myThread1).start();
        new Thread(myThread2).start();
        for (int i = 0; i < 10; i++) {
            System.out.print(MyThread.getArr(i)+",");
        }
    }
}
class MyThread implements Runnable{
    private static int[] arr =new int[10];
    private static int p = 0;
    private int[] array;
    private int i;

    public static int getArr(int index){
        return MyThread.arr[index];
    }
    public MyThread(int[] array) {
        this.array = array;
        this.i=0;
    }

    @Override
    public  void run(){
        while (p<10){
            synchronized(MyThread.class){

                MyThread.arr[p] = this.array [this.i] ;
                MyThread.p++;
                this.i++;
                this.notifyAll();
                try {
                    this.wait();
                } catch (Exception e) {

                }
            }
        }

    }
    //这是交替往arr里加元素

}*/
public class TestThread {
    public static void main(String[] args) throws InterruptedException {
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        MyThread myThread1 = new MyThread(arr1);
        MyThread myThread2 = new MyThread(arr2);
        Thread thread1 = new Thread(myThread1);
        Thread thread2 = new Thread(myThread2);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        for (int i = 0; i < 10; i++) {
            System.out.print(MyThread.getArr(i) + ",");
        }
    }
}

class MyThread implements Runnable {
    private static int[] arr = new int[10];
    private static int p = 0;//指向arr
    private int[] array;
    private int i;//指向array

    public static int getArr(int index) {
        return MyThread.arr[index];
    }

    public MyThread(int[] array) {
        this.array = array;
        this.i = 0;
    }

    @Override
    public void run() {//往arr里添加array里的元素
        while (MyThread.p < 10) {
            synchronized (MyThread.class) {
//                System.out.println(Thread.currentThread().getName()+p);//方便我知道现在是哪个线程在操作
                MyThread.arr[p] = this.array[this.i];//放入元素
                MyThread.p++;//放入元素后指针后移
                this.i++;//放入元素后指针后移
                MyThread.class.notify();
                if (p<10) {
                    try {
                        MyThread.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}