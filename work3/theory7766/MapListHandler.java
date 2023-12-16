package com.west2.work3;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapListHandler implements ResultSetHandler<List<Map<String,Object>>> {
    public List<Map<String,Object>> handle(ResultSet res)throws SQLException{
        List<Map<String,Object>> mapList = new ArrayList<>();
        // 获取结果集元数据对象
        ResultSetMetaData metaData = res.getMetaData();
        int colCnt=metaData.getColumnCount();
        while (res.next()){
            HashMap<String,Object> map = new HashMap<>(colCnt);
            for (int i=1;i<=colCnt;i++){
                // 获得指定索引字段名以及对应的值
                map.put(metaData.getColumnName(i),res.getObject(i));
            }
            mapList.add(map);
        }
        return mapList.isEmpty()?null:mapList;
    }
}
