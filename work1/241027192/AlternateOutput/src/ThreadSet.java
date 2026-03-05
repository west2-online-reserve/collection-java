import java.util.*;

public class ThreadSet {
    public static void main(String[] args) throws InterruptedException {
        List<Object> a = new ArrayList<>();
        Object o=new Object();
        Thread thread1 = new Thread(()->{
            synchronized (o) {
                for (int i = 0; i < 10000; i++) {
                    a.add(i);
                }
            }

        });
        Thread thread2 = new Thread(()->{
            synchronized (o) {
                for (int i = 0; i < 10000; i++) {
                    a.add(i);
                }
            }
        });
        thread1.start();
        thread2.start();
        Thread.sleep(2000);
        System.out.println(a.size());
    }
}
