import java.util.regex.Pattern;

public class BonusFeatures {

    private static final Object lock = new Object();
    private static boolean flag = true;

    public static void printAlternately(int[] arr1, int[] arr2) {
        Thread t1 = new Thread(() -> {
            for (int val : arr1) {
                synchronized (lock) {
                    while (!flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(val + " ");
                    flag = false;
                    lock.notify();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int val : arr2) {
                synchronized (lock) {
                    while (flag) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.print(val + " ");
                    flag = true;
                    lock.notify();
                }
            }
        });

        t1.start();
        t2.start();
    }


    public static boolean isEmailValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}