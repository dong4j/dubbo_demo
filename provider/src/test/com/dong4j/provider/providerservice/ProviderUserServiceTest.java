package com.dong4j.provider.providerservice;

import com.dong4j.provider.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by Code.Ai on 16/7/20.
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class ProviderUserServiceTest {
    @Autowired
    private ProviderUserService providerUserService;
    @Test
    public void testLogin() throws Exception {
        User user = new User();
        user.setUserName("dong4j");
        user.setPassWord("1234");
        user = providerUserService.login(user);
        if(user != null){
            System.out.println(user);
        }else
            System.out.println("登录失败");
    }

    @Test
    public void testGetAllUsers() throws Exception {

    }
}