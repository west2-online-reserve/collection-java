package com.cyx.Bonus;

import java.util.Scanner;

public class email {
    private static boolean isEmail(String email){
        if(email == null){
            return false;
        }
        return email.matches("^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入一个邮箱：");
        String email = sc.nextLine();
        if(isEmail(email)){
            System.out.println("邮箱格式正确！");
        }else{
            System.out.println("邮箱格式错误！");
        }
    }
}
