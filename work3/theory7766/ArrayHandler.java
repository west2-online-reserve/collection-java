package com.west2.work3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArrayHandler implements ResultSetHandler<Object[]>{
    @Override
    public Object[] handle(ResultSet res)throws SQLException {
        List<Object[]> handle =new ArrayListHandler().handle(res);
        return handle!=null?handle.get(0):null;
    }
}
