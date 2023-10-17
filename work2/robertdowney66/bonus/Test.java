package com.west2.check02.bonus;

/***
 * @author yuyu
 */
public class Test {


    public static void main(String[] args) {
        Accept accept = new Accept();
        int[] arr1 = new int[]{1, 3, 5, 7, 9};
        int[] arr2 = new int[]{2, 4, 6, 8, 10};
        accept.acceptNumber(arr1, arr2);

        RegularExpression r1 = new RegularExpression();
        String s = "1234567@qq.com";
        String s2 = "23456666533gmail.com";
        String s3 = "3234254Downey@qq.comh";
        System.out.println(r1.is(s));
        System.out.println(r1.is(s2));
        System.out.println(r1.is(s3));
    }
}
