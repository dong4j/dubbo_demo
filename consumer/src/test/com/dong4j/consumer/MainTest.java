package com.dong4j.consumer;

import com.dong4j.provider.entity.User;
import com.dong4j.provider.providerservice.ProviderUserService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Code.Ai on 16/7/19.
 * Description:
 */

public class MainTest {
    @Test
    public void test() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "spring-mvc.xml" });
        context.start();

        ProviderUserService userService = (ProviderUserService) context.getBean("providerUserService");
        User                user        = new User();
        user.setUserName("dong4j");
        user.setPassWord("1234");
        user       = userService.login(user);
        System.out.println(user);
        System.in.read();
    }
}
