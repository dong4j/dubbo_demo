package com.dong4j.provider.dao;

import com.dong4j.provider.entity.User;

import java.util.List;

/**
 * Created by Code.Ai on 16/7/19.
 * Description:
 */
public interface UserDao {
    User login(User user);
    List<User> getAllUsers();
}
