package com.dong4j.provider.providerservice.imp;

import com.dong4j.provider.common.ZookeeperClient;
import com.dong4j.provider.providerservice.ProviderUserService;
import com.dong4j.provider.entity.User;
import com.dong4j.provider.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Code.Ai on 16/7/19.
 * Description:
 */
@Service
public class ProviderUserServiceImp implements ProviderUserService {
    Logger logger = LoggerFactory.getLogger(ProviderUserServiceImp.class);
    @Autowired
    private UserService     userService;
    @Autowired
    private ZookeeperClient zookeeperClient;
    public User login(User user) {
        logger.info("服务提供者的服务被调用");
        try {
            zookeeperClient.connectZookeeper();
            System.out.println(zookeeperClient.getDataSourceKey().getDefaultKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userService.login(user);
    }

    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
