package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Weather;
import org.example.pojo.Weather;

import java.util.List;

public interface WeatherMapper {
    void add(Weather c);
    void deleteByIds(@Param("ids") List<Integer> ids);
    public List<Weather> selectAll();
    @Select("select *from weather where id=#{id}")
    List<Weather> selectById(int id);
    List<Weather> selectByIds(@Param("ids") List<Integer> ids);
    void update(Weather weather);

}
