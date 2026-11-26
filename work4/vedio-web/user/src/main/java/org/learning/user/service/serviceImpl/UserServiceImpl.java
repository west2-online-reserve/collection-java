package org.learning.user.service.serviceImpl;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.learning.user.mapper.UserMapper;
import org.learning.user.pojo.Info;
import org.learning.user.pojo.LoginInfo;
import org.learning.user.pojo.User;
import org.learning.user.service.UserService;
import org.learning.user.utils.JwtUtil;
import org.learning.user.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        //用uuid生成
        user.setId(UUID.randomUUID().toString());
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    @Transactional
    public LoginInfo login(User user){

        try{

            if(userMapper.findByUsernameAndPassword(user)==null){
                throw new RuntimeException("用户名或密码不正确");
            }else{
                log.info("生成令牌...");
                //生成令牌
                Map<String,Object> claims=new HashMap<>();
                claims.put("id",user.getId());
                claims.put("username",user.getUsername());

                String jwt=JwtUtil.createToken(user.getId(),claims);
                logger.debug("jwt:{}",jwt);
                log.info("令牌生成成功,继续登录...");
                UserContextHolder.setCurrentUser(user);

                return new LoginInfo(user.getId(), user.getUsername(), user.getAvatarUrl(), user.getCreatedAt(),user.getUpdatedAt(),user.getDeletedAt(),jwt);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Info getInfo(String id){
        User user=userMapper.findById(id);
        logger.info("用户:{}",user.getUsername());
        logger.info("头像:{}",user.getAvatarUrl());
        logger.info("注册时间:{}",user.getCreatedAt());
        Info info=new Info(user.getId(), user.getUsername(), user.getAvatarUrl(), user.getCreatedAt(),user.getUpdatedAt(),user.getDeletedAt());
        return info;
    }

    @Override
    public Info upload(MultipartFile file) {
        try{
            String fileName = file.getOriginalFilename();

            if(fileName.contains("jpg")||fileName.contains("jpeg")||fileName.contains("png")||fileName.contains("gif")){
                log.info("获取当前登录的用户信息");
                User user=UserContextHolder.getCurrentUser();
                log.info("获取成功");
                log.info("当前用户:{}",user.getUsername());

                user.setHeadImage(file);
                //保存文件到服务端本地的目录

                String completeName="D:/images/"+file.getOriginalFilename();
                file.transferTo(new File(completeName));
                user.setAvatarUrl(completeName);
                user.setUpdatedAt(LocalDateTime.now());
                userMapper.update(user);
                log.info("上传头像成功");


                Info info=new Info(user.getId(), user.getUsername(), user.getAvatarUrl(), user.getCreatedAt(),user.getUpdatedAt(),user.getDeletedAt());
                return info;
            }else{
                throw new RuntimeException("格式错误");
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
