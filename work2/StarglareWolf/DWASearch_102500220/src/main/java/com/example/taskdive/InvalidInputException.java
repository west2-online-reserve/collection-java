package com.example.taskdive;

// 单独命名的一个错误，表示参数数量不匹配
public class InvalidInputException extends Exception {
    public InvalidInputException(String message){
        super(message);
    }
}