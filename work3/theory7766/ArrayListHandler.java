package com.west2.work3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArrayListHandler implements ResultSetHandler {
    public List<Object[]> handle(ResultSet res) throws SQLException {
        List<Object[]> list = new ArrayList<>();
        int colCnt = res.getMetaData().getColumnCount();
        while (res.next()) {
            Object[] arr = new Object[colCnt];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = res.getObject(i + 1);
            }
            list.add(arr);
        }
        return list.isEmpty() ? null : list;
    }
}
