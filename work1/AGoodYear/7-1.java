import  java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cm = scanner.nextInt();
        double totalFoot = cm / 100.0 / 0.3048;
        int foot = (int)totalFoot;
        int inch = (int)((totalFoot - foot) * 12);
        System.out.println(foot + " " + inch);
    }
}