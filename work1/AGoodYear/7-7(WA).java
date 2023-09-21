import  java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String temp = scanner.next();
        temp = temp.replace(":", "");
        int time = Integer.parseInt(temp);
        int hour, min;
        if (time < 100){
            hour = time / 10;
            min = time - hour * 10;
        } else if (time - (time / 10) * 10 == 0 && time < 1000){
            hour = time / 10;
            min = 0;
        } else {
            hour = time / 100;
            min = time - hour * 100;
        }
        boolean isPM = false;
        if (hour >= 12){
            isPM = true;
        }
        if (hour > 12){
            hour -= 12;
        }
        System.out.print(hour + ":" + min);
        if (isPM == false){
            System.out.print(" AM");
        } else {
            System.out.print(" PM");
        }
    }
}