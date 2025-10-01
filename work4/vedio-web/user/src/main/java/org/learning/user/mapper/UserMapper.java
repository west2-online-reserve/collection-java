package org.learning.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import org.learning.user.pojo.User;

@Mapper
public interface UserMapper {

    void insert(User user);

    User findByUsernameAndPassword(User user);

    User findById(String id);

    void update(User user);
}
