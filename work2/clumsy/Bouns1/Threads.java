package Bouns1;

public class Threads {
    private static int[] arr1={1,3,5,7,9};
    private static int[] arr2={2,4,6,8,10};
    private static boolean flag=true;
    public static final Object lock = new Object();

    public static class Thread1 extends Thread {


        @Override
        public void run() {
            synchronized (lock) {
                while (flag == true) {
                    for (int i = 0; i < arr1.length; i++) {
                        System.out.print(arr1[i] + " ");
                        try {
                            Thread1.sleep(100);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        flag = false;
                        lock.notifyAll();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                }

            }
        }
    }

    public static class Thread2 extends Thread {

        @Override
        public void run() {
            synchronized (lock){
                while(flag==false){
                for(int i=0;i<arr2.length;i++){
                    System.out.print(arr2[i]+" ");
                    try {
                        Thread2.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if(arr2[i]==10){
                        flag=true;
                        break;
                    }
                    flag=true;
                    lock.notifyAll();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }


                }
            }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException, Exception {
        Thread1 t1=new Thread1();
        Thread2 t2=new Thread2();
        t1.start();
        t2.start();
    }
}

