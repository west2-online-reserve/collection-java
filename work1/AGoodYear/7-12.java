import  java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n1 = scanner.nextInt();
        String process = scanner.next();
        int n2 = scanner.nextInt();
        int result = 0;
        switch (process) {
            case "+":
                result = n1 + n2;
                System.out.println(result);
                break;
            case  "-":
                result = n1 - n2;
                System.out.println(result);
                break;
            case "*":
                result = n1 * n2;
                System.out.println(result);
                break;
            case "/":
                result = n1 / n2;
                System.out.println(result);
                break;
            case "%":
                result = n1 % n2;
                System.out.println(result);
                break;
            default:
                System.out.println("ERROR");
                break;
        }
    }
}