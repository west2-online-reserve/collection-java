package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.City;
import org.example.pojo.User;

import java.util.List;

public interface UserMapper {
    void add(City c);
    void deleteById(int id);
    void deleteByIds(@Param("ids") List<Integer> ids);
    void deleteByNames(@Param("names") List<String> names);

    public List<City> selectAll();
    @Select("select *from tb_user where id=#{id}")
    City selectById(int id);
    List<City> selectByIds(@Param("ids")List<Integer> ids);

    List<City> selectByNames(@Param("names")List<String> names);
    public List<City> selectByCondition(@Param("lat") double lat,@Param("lon") double lon);

    List<City> selectByCondition(City m);

    void update(City city);


}
