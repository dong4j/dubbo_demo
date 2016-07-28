package com.dong4j.provider.service.imp;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.dong4j.provider.common.ZookeeperClient;
import com.dong4j.provider.dao.UserDao;
import com.dong4j.provider.entity.User;
import com.dong4j.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Code.Ai on 16/7/19.
 * Description:
 */

@Service
public class UserServiceImp implements UserService {
    Logger log = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserDao userDao;
    public User login(User user) {
        log.debug("login");
        return userDao.login(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
