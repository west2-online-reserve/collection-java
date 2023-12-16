package com.west2.work3;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultSetHandler<T> {
    /**
     * 结果集处理方法
     *
     * @param res 结果集对象
     * @return 泛型约束，由实现类决定最终返回的数据
     * @throws SQLException SQL异常
     */
    T handle(ResultSet res) throws SQLException;
}
