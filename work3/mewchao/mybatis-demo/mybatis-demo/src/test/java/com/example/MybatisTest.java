package com.example;

import com.example.mapper.GoodMapper;
import com.example.pojo.Good;
import com.example.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class MybatisTest {

    @Test
    public void test() throws IOException {
//        DateTime time = new DateTime(2018,4,23,23, 7,18,888);
//
//        Good good=new Good(1,1.0,"book1",time,time);

        SqlSession sqlSession = MybatisUtil.getSqlSession();

        GoodMapper goodMapper = sqlSession.getMapper(GoodMapper.class);

//        goodMapper.insertGood(good);

        List<Good> goods = goodMapper.getGoodList();

        goods.forEach(System.out::println);

        sqlSession.close();
    }
}
