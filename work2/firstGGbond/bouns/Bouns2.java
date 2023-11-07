package bouns;
public class Bouns2 {
    public static void main(String[] args) {
       int[] arr1 = {1, 3, 5, 7, 9};
	   int[] arr2 = {2, 4, 6, 8, 10};

	   interleavedOutput(arr2, arr1);
	}
	   public static void interleavedOutput(int[] arr1, int[] arr2) {
	        Object lock = new Object();
	        Thread thread1 = new Thread(() -> {
	            synchronized (lock) {
	                for (int i : arr1) {
	                    System.out.print(i + " ");
	                    try {
	                        lock.notify(); 
	                        lock.wait(); //保证只有一个线程运行。
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        });

	        Thread thread2 = new Thread(() -> {
	            synchronized (lock) {
	                for (int i : arr2) {
	                    System.out.print(i + " ");
	                    try {
	                        lock.notify();
	                        lock.wait();
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        });
	        thread1.start();
	        thread2.start();
	        try {
	            thread1.join();
	            thread2.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
       }
}
