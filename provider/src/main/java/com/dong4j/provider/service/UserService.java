package com.dong4j.provider.service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.Log;
import com.dong4j.provider.entity.User;

import java.util.List;

/**
 * Created by Code.Ai on 16/7/19.
 * Description:
 */
public interface UserService {
    User login(User user);
    List<User> getAllUsers();
}
