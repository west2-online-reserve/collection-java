package Bonus_1;

import java.util.Scanner;



public class TestM {
    public static void main(String[] args) {
        int[] arr1=new int[5];
        int[] arr2=new int[5];
        Scanner sc=new Scanner(System.in);
        for (int i = 0; i < arr1.length; i++) {
            arr1[i]=sc.nextInt();
        }
        for (int i = 0; i < arr2.length; i++) {
            arr2[i]=sc.nextInt();
        }
        mul(arr1,arr2);

    }
    public static void mul(int arr1[],int arr2[]){
        Runnable target1 =new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    System.out.print(arr2[i]);
                }
            }
        };
        new Thread(target1).start();

        Runnable target2 =new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < arr2.length; i++) {
                    System.out.print(arr1[i]);
                }
            }
        };
        new Thread(target2).start();
    }


}
