package org.learning.user.service;

import org.learning.user.pojo.Info;
import org.learning.user.pojo.LoginInfo;
import org.learning.user.pojo.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    void  register(User user);
    LoginInfo login(User user);
    Info getInfo(String id);
    Info upload(MultipartFile file);
}
