package westwork2;

public class Bonus {

    // ...其他代码...

    // Bonus part 1: 交替输出两个数组的元素
    public static void alternatePrint(int[] arr1, int[] arr2) {
        Object lock = new Object();

        Thread t1 = new Thread(() -> {
            for (int value : arr1) {
                synchronized (lock) {
                    System.out.print(value + " ");
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            synchronized (lock) {
                lock.notify(); // 确保最后一个等待的线程可以退出
            }
        });

        Thread t2 = new Thread(() -> {
            for (int value : arr2) {
                synchronized (lock) {
                    System.out.print(value + " ");
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            synchronized (lock) {
                lock.notify(); // 确保最后一个等待的线程可以退出
            }
        });

        t1.start();
        t2.start();
    }

    // Bonus part 2: 邮箱格式验证
    public static boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

    public static void main(String[] args) {
        // ...其他代码...

        // Bonus 测试
        int[] arr1 = {1, 3, 5, 7, 9};
        int[] arr2 = {2, 4, 6, 8, 10};
        alternatePrint(arr1, arr2);

        // 邮箱验证测试
        System.out.println("\nIs 'hello@example.com' a valid email? " + isEmailValid("hello@example.com"));
        System.out.println("Is 'hello.example' a valid email? " + isEmailValid("hello.example"));
    }
}
