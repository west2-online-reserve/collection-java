package bonus1;

public class Thread2 extends Thread {
    private Thread thread02;
    private int[] arr;

    Thread2(int[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        for (int i = 0; i < arr.length; ++i) {
            synchronized (Lock.object) {
                System.out.println(arr[i]);
                Lock.object.notify();
                try {
                    if (i == arr.length - 1) {
                        Lock.object.notifyAll();
                        return;
                    }

                    Lock.object.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
