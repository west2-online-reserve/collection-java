package com.west2.work3;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class MapHandler implements ResultSetHandler<Map<String, Object>> {
    @Override
    public Map<String, Object> handle(ResultSet res) throws SQLException {
        List<Map<String, Object>> mapList = new MapListHandler().handle(res);
        return mapList != null ? mapList.get(0) : null;
    }
}


