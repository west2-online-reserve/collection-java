package org.example.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.City;

import java.util.List;

public interface CityMapper {
    void add(City c);
    void deleteById(int id);
    void deleteByIds(@Param("ids") List<Integer> ids);
    void deleteByNames(@Param("names") List<String> names);

    public List<City> selectAll();
    @Select("select *from city where id=#{id}")
    City selectById(int id);
    List<City> selectByIds(@Param("ids")List<Integer> ids);

    City selectByName(String name);

    List<City> selectByNames(@Param("names")List<String> names);
    public City selectByCondition(@Param("lat") double lat,@Param("lon") double lon);

    List<City> selectByCondition(City m);

    void update(City city);


}
