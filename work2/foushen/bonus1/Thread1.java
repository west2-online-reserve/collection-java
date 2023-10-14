package bonus1;

public class Thread1 extends Thread {
    private Thread thread1;
    private int[] arr;

    Thread1(int[] arr) {
        this.arr = arr;

    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; ++i) {
            synchronized (Lock.object) {
                System.out.println(arr[i]);
                Lock.object.notify();

                try {
                    //如果i是最后一个元素，则不进入等待
                    if (i == arr.length - 1) {
                        return;
                    }
                    Lock.object.wait();

                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
