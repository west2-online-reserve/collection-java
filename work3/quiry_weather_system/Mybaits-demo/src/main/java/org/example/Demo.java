package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.User;
import org.example.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws IOException {
        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3. 执行sql，这步不用记，一般这步都是使用mapper代理开发       sqlSession.getMapper(UserMapper.class);
        //参数是一个字符串，该字符串必须是映射配置文件的namespace.id
       // List<User> users = sqlSession.selectList("test.selectAll");
       // System.out.println(users);
        UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
        List<User>users=userMapper.selectAll();
        //4. 释放资源
        sqlSession.close();
    }
}