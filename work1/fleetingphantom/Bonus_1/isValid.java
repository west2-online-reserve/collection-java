package Bonus_1;

import java.util.ArrayList;
import java.util.Scanner;

public class isValid {
    public static void main(String[] args) {
        //正则判断邮箱
        //317363049@qq.com
        //222@qq.com
        Scanner sc=new Scanner(System.in);
        String str;
        str=sc.next();
        if(isValidEmail(str)){
            System.out.println("邮箱合法");
        }
        else {
            System.out.println("邮箱不合法");
        }


    }

    public static boolean isValidEmail(String str) {
        if (str.matches("\\w{2,123}@\\w{2,20}(\\.\\w{2,10}){1,2}")) {
            return true;
        }
        return false;

    }

}


