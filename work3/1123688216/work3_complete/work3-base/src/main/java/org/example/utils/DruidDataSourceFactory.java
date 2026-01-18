package org.example.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

public class DruidDataSourceFactory extends UnpooledDataSourceFactory {
    public DruidDataSourceFactory() {
        // 核心：把 MyBatis 默认的数据源替换成 Druid 的数据源
        this.dataSource = new DruidDataSource();
    }
}