package com.example.mapper;

import com.example.pojo.Good;

import java.util.List;

public interface GoodMapper {
    List<Good> getGoodList();

    void insertGood(Good good);
}
