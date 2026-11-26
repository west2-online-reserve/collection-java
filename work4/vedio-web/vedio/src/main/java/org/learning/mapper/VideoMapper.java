package org.learning.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.learning.pojo.Video;

import java.util.List;

@Mapper
public interface VideoMapper {
    List<Video> findAll();
    Video findById(String id);
    void save(Video video);
    void update(Video video);
    void deleteById(String id);
}
