public class ThreadDemo {
    private static volatile int currentNumber = 1;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        int[] oddNumbers = {1, 3, 5, 7, 9};
        int[] evenNumbers = {2, 4, 6, 8, 10};
        startThreads(oddNumbers, evenNumbers);
    }

    public static void startThreads(int[] odd, int[] even) {
        Runnable oddTask = new Task(odd);
        Runnable evenTask = new Task(even);

        Thread t1 = new Thread(oddTask, "Thread-Odd");
        Thread t2 = new Thread(evenTask, "Thread-Even");
        t1.start();
        t2.start();
    }

    static class Task implements Runnable {
        private final int[] numbers;

        public Task(int[] numbers) {
            this.numbers = numbers;
        }

        @Override
        public void run() {
            for (int num : numbers) {
                synchronized (lock) {
                    while (num != currentNumber) {
                        try {
                            lock.wait(); 
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + " prints: " + num);
                    currentNumber++;
                    lock.notifyAll(); 
                }
            }
        }
    }
}