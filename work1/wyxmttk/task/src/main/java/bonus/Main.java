package bonus;

import petShop.customer.Customer;

public class Main {
    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 7, 9,11}, arr2 = {2, 4, 6, 8, 10};
        printNumber(arr1,arr2);
        System.out.println(validateEmail("2849173390@qq.com"));
        System.out.println(validateEmail("2849173390@qq..com"));
        System.out.println(validateEmail("2849173390@qq.c.c.co"));
        System.out.println(validateEmail("2849173390@qq-.com"));
        System.out.println(validateEmail("2849173390@qq.com-"));
    }
    public static final String BASIC_EMAIL="^[A-Za-z0-9!#$%&'*+/=?^_`{|}~.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    public static boolean validateEmail(String email) {
        if (email == null || email.isBlank()) return false;
        if (email.length() > 254) return false;
        if(!email.matches(BASIC_EMAIL)) return false;

        int atIndex = email.lastIndexOf('@');
        if (atIndex <= 0 || atIndex == email.length() - 1) return false;


        String local = email.substring(0, atIndex);
        String domain = email.substring(atIndex + 1);

        if (local.length() > 64) return false;

        if (local.startsWith(".") || local.endsWith(".")) return false;
        if (local.contains("..")) return false;

        if (domain.startsWith(".") || domain.endsWith(".") || domain.contains("..")) return false;

        String[] labels = domain.split("\\.");
        //至少1个点分隔
        if (labels.length < 2) return false;
        for (String label : labels) {
            if (label.isEmpty() || label.length() > 63) return false;
            // 允许字母数字和中间的连字符，但不能结尾为 '-'
            if (!label.matches("^[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?$")) return false;
        }

        // 最后1个label最少2个字母
        String tld = labels[labels.length - 1];
        if (!tld.matches("^[A-Za-z]{2,}$")) return false;


        return true;
    }
    public static int index=0;
    public static final Object lock=new Object();
    public static void printNumber(int[]arr1,int[]arr2){
        int len=arr1.length+arr2.length;
        Thread t1 = new Thread(() -> {
            synchronized (lock) {
                while(index<len){
                    if(index%2==0){
                        System.out.println(arr1[index/2]);
                        index++;
                        lock.notify();
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lock.notify();
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock) {
                while(index<len){
                    if(index%2==1){
                        System.out.println(arr2[index/2]);
                        index++;
                        lock.notify();
                    }
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                lock.notify();
            }
        });
        t1.start();
        t2.start();


    }
}
