import cn.hutool.json.JSONObject;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Connection.Dbconnection;
import org.example.Utils.OkHttpApi;
import org.example.mapper.UserMapper;
import org.example.pojo.User;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class testSelectById {
    @Test
    public void test() throws Exception {
       /* int id=2;
        String username="李";
        username=username+"%";
        User user=new User();
        user.setId("102");
        user.setUsername("wangwu");
        user.setPASSWORD("123456");
        user.setGender("g");
        user.setAddr("wuhan");


        //1. 加载mybatis的核心配置文件，获取 SqlSessionFactory
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        //2. 获取SqlSession对象，用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //3. 执行sql
        //参数是一个字符串，该字符串必须是映射配置文件的namespace.id
//        List<User> users = sqlSession.selectList("test.selectAll");
        //3.1获取UserMapper接口的代理对象
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        //3.2调用sql方法
        int []ids={100,101};
       //userMapper.update(user);
       // userMapper.deleteById(8);
//        userMapper.deleteByIds(ids);
//        List<User> users=userMapper.selectAll();
//        System.out.println(users);
        User user1=userMapper.selectById(id);
        System.out.println(user1);
        //4. 释放资源
        sqlSession.close();*/
        OkHttpApi okHttpApi=new OkHttpApi();
        String run=okHttpApi.run("https://devapi.qweather.com/v7/weather/3d?key=29f50da3987a40d0bc6298fd74e9a454&location=101230906");
        System.out.println(run);
        /*JSONObject jsonObject=JSONObject.parseObject(run);
        System.out.println(jsonObject);
        String content=jsonObject.getString("content");
        System.out.println(content);*/
    }
    }

