package com.PeanutJava.task2.Bonus2;

public class Bonus2 {
    public static void main(String[] args) {
        test();
    }

    public static boolean Check(String s){

        String ret="\\d{0,9}@(qq|tom|gmail|hotmail|icloud).com";
        return s.matches(ret);
    }

    public static boolean test(){
        return (Check("775075825@qq.com"));
    }
}
