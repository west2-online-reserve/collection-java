package org.example.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtils {
        private static SqlSessionFactory sqlSessionFactory;

        static {
            try {
                String resource = "mybatis-config.xml";
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("MyBatis 初始工厂失败");
            }
        }

        public static SqlSession getSqlSession() {
            // 默认开启事务，不自动提交
            return sqlSessionFactory.openSession();
        }
}
