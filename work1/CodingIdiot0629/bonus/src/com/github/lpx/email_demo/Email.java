package com.github.lpx.email_demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    String account;

    Email(String account) {
        this.account = account;
    }

    public static boolean CheckEmail(String email) {
        Pattern emailPattern = Pattern.compile("(?!.{255,})(?!\\..*)(?!.*\\.@)(?!.*\\.\\.)^[\\w.]+@[\\w.]+\\.\\w{2,}");
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
        //多次调用的话下面方法性能较差，但它更简洁
        //return email.matches("(?!.{255,})(?!\\..*)(?!.*\\.@)(?!.*\\.\\.)^[\\w.]+@[\\w.]+\\.\\w{2,}");
    }
}
