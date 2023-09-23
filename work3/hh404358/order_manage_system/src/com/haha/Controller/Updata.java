package com.haha.Controller;

import com.haha.Connection.DbConnection;

//判断是否更新数据
public class Updata {
    //添加数据
    public static int upData(String sql){
        return DbConnection.updateInfo(sql);
    }

}
