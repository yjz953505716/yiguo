package com.qfedu.yiguo.dao;

import com.qfedu.yiguo.entity.User;

public interface UserDao {

    public void addUser(User user);

    public User findByPhone(String phone);

    public User updateUser(User user);

}
