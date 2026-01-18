package org.example.mapper;

import org.example.pojo.Commodity;

import java.util.List;

public interface CommodityMapper {
    //增删改查
    void add(Commodity commodity);
    void deleteById(int id);
    void update(Commodity commodity);
     List<Commodity> queryAll();

    List<Commodity> queryAllShop();

    Commodity queryById(int id);

}
