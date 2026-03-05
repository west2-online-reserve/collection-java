import java.util.LinkedList;
import java.util.Queue;

public class Production {
    private static final Queue<Object> queue=new LinkedList<>();
    public static void main(String[] args) {
        new Thread(Production::add,"厨师1").start();
        new Thread(Production::add,"厨师2").start();
        new Thread(Production::take,"消费者1").start();
        new Thread(Production::take,"消费者2").start();
    }
    private static void add(){
        while(true){
            try {
                Thread.sleep(3000);
                synchronized (queue){
                    if(queue.isEmpty()){
                        String name=Thread.currentThread().getName();
                        System.out.println(name + "出餐了");
                        queue.offer(new Object());
                        queue.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private static void take(){
        try {
            synchronized (queue){
                while(true){
                    while(queue.isEmpty()) queue.wait();
                    queue.poll();
                    String name=Thread.currentThread().getName();
                    System.out.println(name+"消费拿走了一份");
                    Thread.sleep(4000);
                }
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }
}
