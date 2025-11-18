package com.amiera.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class utils {

        // sqlSessionFactory工具类
        private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            System.err.println("初始化SqlSessionFactory失败: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("无法初始化MyBatis配置", e);
        }
    }

    // 获取SqlSession实例
    public static SqlSession getSqlSession() {
        try {
            if (sqlSessionFactory == null) {
                throw new IllegalStateException("SqlSessionFactory未初始化");
            }
            return sqlSessionFactory.openSession();
        } catch (Exception e) {
            System.err.println("获取SqlSession失败: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("无法获取数据库会话", e);
        }
    }
}
