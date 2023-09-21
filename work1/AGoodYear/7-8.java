import  java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int speed = scanner.nextInt();
        System.out.print("Speed: " + speed + " - ");
        if (speed > 60){
            System.out.print("Speeding");
        } else {
            System.out.print("OK");
        }
    }
}