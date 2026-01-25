public class Parallel {
    public static void main(String[] args) throws InterruptedException {
        Object o=new Object();
        Thread thread1 = new Thread(()->{
            int[] a={1,3,5,7,9};
            synchronized (o) {
                for (int i : a) {
                    try {
                        System.out.println(i);
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Thread thread2 = new Thread(()->{
            int[] a={2,4,6,8,10};
            synchronized (o) {
                for (int i : a) {
                    try {
                        o.wait();
                        System.out.println(i);
                        o.notify();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread2.start();
        //确保thread2拿到锁
        Thread.sleep(100);
        thread1.start();

    }
}
