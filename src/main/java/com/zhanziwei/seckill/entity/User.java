package com.zhanziwei.seckill.entity;

public class User {
    String user_name;
    String password;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                '}';
    }
}
