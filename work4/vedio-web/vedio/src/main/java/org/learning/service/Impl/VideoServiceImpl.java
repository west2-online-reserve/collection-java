package org.learning.service.Impl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.learning.mapper.VideoMapper;
import org.learning.pojo.Video;
import org.learning.service.VideoSeivice;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class VideoServiceImpl implements VideoSeivice {
    @Resource
    private VideoMapper videoMapper;
    @Override
    public List<Video> getVideoList(){
        try{
            log.info("获取视频列表");
            List<Video> videos=videoMapper.findAll();
        }catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
}
