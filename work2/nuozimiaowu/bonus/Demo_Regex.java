package com.sty.bonus;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo_Regex {
    public static void main(String[] args) {

        String email = "141240asdf@qq.com";
        //   \w+  字母数字下划线出现多次    [@]\w+(\.\w+)+
        boolean matchesemail = email.matches("\\w+[@]\\w+(\\.\\w+)+");
        System.out.println(matchesemail);
    }

}
