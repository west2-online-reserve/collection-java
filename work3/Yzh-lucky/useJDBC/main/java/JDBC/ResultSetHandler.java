package JDBC;

import java.sql.ResultSet;

//用来处理查询的输出结果
public interface ResultSetHandler<T> {
    T handle(ResultSet rs);
}
