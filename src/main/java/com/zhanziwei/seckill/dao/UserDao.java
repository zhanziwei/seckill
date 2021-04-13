package com.zhanziwei.seckill.dao;

import com.zhanziwei.seckill.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User queryUser(@Param("userName") String username,@Param("password") String password);
    int insertUser(@Param("user") User user);
    void insertUsers(@Param("users") List<User> userList);
}
