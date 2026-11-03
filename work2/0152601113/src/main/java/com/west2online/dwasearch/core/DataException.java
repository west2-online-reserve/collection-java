package com.west2online.dwasearch.core;

/**
 * 数据相关的异常：读取、解析、校验 data.json 失败时抛出。
 *
 * 用自定义异常，是为了把「数据出问题」和普通的文件读写错误区分开，
 * 让调用方（程序入口）能给出更清楚的提示。
 */
public class DataException extends RuntimeException {

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
