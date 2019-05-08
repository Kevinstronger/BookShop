package com.test.dao;

import com.test.entity.User;

/**
 * Created by Administrator on 2017/5/14.
 */
public interface UserDao {
    void addUser(User user);
    User findUserById(String userId);
    User findUserByName(String userName, String password);
}
