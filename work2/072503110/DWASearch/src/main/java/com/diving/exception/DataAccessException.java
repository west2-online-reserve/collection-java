package com.diving.exception;

//用于封装底层 IO 或网络异常，避免直接抛出底层异常给上层
//子类的构造方法必须调用父类的构造方法
public class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);//调用了父类RuntimeException的构造方法
    }
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}

