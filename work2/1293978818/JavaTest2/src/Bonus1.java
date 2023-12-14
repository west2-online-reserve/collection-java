/**
 * 实现用多线程的方法交替输出数组
 * 
 * @author 1293978818
 */
public class Bonus1{
    
    

    public static void main(String[] args) {

        int arr1[] = { 1, 3, 5, 7, 9 };
        int arr2[] = { 2, 4, 6, 8, 10 };
        
        Bonus1 bonus1 = new Bonus1();

        // TODO 未使用线程池
        Thread thread1 = new Thread(new Thread(){
            public void run(){
                synchronized(bonus1){
                    for(int i = 0;i <arr1.length;i ++){
                        System.out.println(arr1[i]);
                        try {
                            bonus1.notify();
                            bonus1.wait();
                            
                        } catch (InterruptedException e) {
                            
                            e.printStackTrace();
                        }
                        
                    }
                }
            }
        });

        // TODO 未使用线程池
        Thread thread2 = new Thread(new Thread(){
            public void run(){
                synchronized(bonus1){
                    for(int i = 0;i <arr2.length;i ++){
                        System.out.println(arr2[i]);
                        try {
                            bonus1.notify();
                            bonus1.wait();
                        } catch (InterruptedException e) {
                            
                            e.printStackTrace();
                        }
                        
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}