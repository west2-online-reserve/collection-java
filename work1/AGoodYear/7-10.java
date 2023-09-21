import  java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int age = scanner.nextInt();
        int work = scanner.nextInt();
        double salary;
        if (age >= 5) {
            if (work < 40){
                salary = work * 50;
            } else {
                salary = 2000 + (work - 40) * 50 * 1.5;
            }
        } else {
            if (work < 40){
                salary = work * 30;
            } else {
                salary = 1200 + (work - 40) * 30 * 1.5;
            }
        }
        System.out.printf("%.2f", salary);
    }
}