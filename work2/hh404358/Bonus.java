//bonus task
class Bonus{
    //bonus1:多线程输出
    public void test1(){
        final Object obj=new Object();
        int arr1[]={1,3,5,7,9};
        int arr2[]={2,4,6,8,0};
        /*//    方法一wait&notify
        //注意这里的synchronized不能使用this，因为这是在静态方法中，这里除了可以使用自定义的监视器，还可以使用类的Class对象。
        //无法正确保证是先输出1还是2
        new Thread(()->{
            synchronized (obj){
                for (int num:arr1) {
                    System.out.print(num+" ");
                    try{
                        obj.notify();
                        obj.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                obj.notify();
            }
        }).start();
        System.out.print('\');
        new Thread(()->{
            synchronized (obj){
                for (int num:arr2) {
                    System.out.print(num+" ");
                    try{
                        obj.notify();
                        obj.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                obj.notify();
            }
        }).start();*/

        //方法二 ReentrantLock
        Lock lock=new ReentrantLock();
        Condition condition1= lock.newCondition();
        Condition condition2= lock.newCondition();
        new Thread(()->{
            lock.lock();
            try{
                for (int i = 0; i < arr1.length; i++) {
                    System.out.println(arr1[i]+" ");
                    condition2.signal();
                    condition1.await();
                }
                condition1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }).start();
        new Thread(()->{
            lock.lock();
            try{
                for (int i = 0; i < arr2.length; i++) {
                    System.out.println(arr2[i]+" ");
                    condition1.signal();
                    condition2.await();
                }
                condition2.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                lock.unlock();
            }
        }).start();
    }

    //bonus2:正则表达式检验邮箱
    public static boolean CheckBox(String s){
        /*  权当注释
        String ret1="\\d{0,10}\\@qq.com";//qq邮箱
        String ret2="\\d{0,10}\\@tom.com";//TOM
        String ret3="\\d{0,10}\\@gmail.com";//谷歌邮箱
        String ret4="\\d{0,10}\\@hotmail.com";//微软
        String ret5="\\d{0,10}\\@icloud.com";//苹果邮箱*/

        /*  第二种想法没有充分运用正则表达式
        String box[]={"qq","tom","gamil","hotmail","icloud"};
        String ret="";
        for(int i=0;i<box.length;i++)
        ret="\\d{0,10}\\@"+box[i]+"\\.com";*/

        String ret="\\d{0,10}@(qq|tom|gmail|hotmail|icloud).com";
        return s.matches(ret);
    }

    public boolean test2(){
        return (CheckBox("1234567890@qq.com"));
    }

}


public class hello {
    public static void main(String[] args) throws InterruptedException {
       
        Bonus bonus=new Bonus();
        bonus.test1();



    }
}
