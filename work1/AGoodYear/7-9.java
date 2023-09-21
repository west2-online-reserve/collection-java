import  java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ball1 = scanner.nextInt();
        int ball2 = scanner.nextInt();
        int ball3 = scanner.nextInt();
        if (ball2 == ball3){
            System.out.print("A");
        } else if (ball1 == ball2) {
            System.out.print("C");
        } else if (ball1 == ball3) {
            System.out.print("B");
        }
    }
}