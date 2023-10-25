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
                Lock.object.notify();
                try {
                    System.out.println(arr[i] + " ");
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
