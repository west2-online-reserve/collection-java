class ThreadToPrint extends Thread {
    private int[] arr;
    private static final Object lock = new Object();

    public ThreadToPrint(int[] arr) {
        this.arr = arr;
    }
    public void run() {
        synchronized (lock) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
                lock.notify();
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            lock.notify();
        }
    }
}
