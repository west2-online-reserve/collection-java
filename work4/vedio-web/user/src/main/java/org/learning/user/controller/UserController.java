package org.learning.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.learning.user.pojo.Info;
import org.learning.user.pojo.LoginInfo;
import org.learning.user.pojo.Result;
import org.learning.user.pojo.User;
import org.learning.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /*
        注册功能
     */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        logger.debug("registing...");
        userService.register(user);
        logger.debug("register success...");
        return Result.success();
    }
    /*
        登录功能
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        logger.debug("login...");
        LoginInfo loginInfo=userService.login(user);
        if(loginInfo!=null){
            logger.debug("login success...");
            return Result.success(loginInfo);
        }else{
            logger.debug("login failed...");
            return null;
        }
    }
    /*
        获取用户信息
     */
    @GetMapping("/info")
    public Result getInfo(String id){
        Info info=userService.getInfo(id);
        return Result.success(info);
    }
    /*
        上传头像
     */
    @PutMapping("/avatar/upload")
    public Result uploadImage(User user, MultipartFile file){
        logger.info("uploading...");
        Info info=userService.upload(file);
        return Result.success(info);
    }
}
