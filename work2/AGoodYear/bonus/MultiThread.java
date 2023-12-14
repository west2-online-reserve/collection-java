/**
 * 使用多线程交替输出两个数组中的数字
 *
 * @author AGoodYear
 * @date 2023/11/4
 */
public class MultiThread extends Thread {
    private int[] arr;
    private int n;
    private final Object lock;

    MultiThread(int[] arr, int n, Object obj) {
        this.arr = arr;
        this.n = n;
        lock = obj;
    }

    @Override
    public void run() {
        int i=0;
        while (i<arr.length) {
            synchronized (lock) {
                    try {
                        System.out.println("线程" + n +"打印：" + arr[i]);
                        i++;
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }
        }

    public static void main(String[] args) {
        int[] arr1 = {1,3,5,7,9};
        int[] arr2 = {2,4,6,8,10};
        Object obj = new Object();
        MultiThread thread1 = new MultiThread(arr1, 1, obj);
        MultiThread thread2 = new MultiThread(arr2, 2, obj);
        thread1.start();
        thread2.start();
    }
}

