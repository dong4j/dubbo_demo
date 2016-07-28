package com.dong4j.provider.controller;

import com.dong4j.provider.entity.User;
import com.dong4j.provider.providerservice.ProviderUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Code.Ai on 16/7/19.
 * Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private ProviderUserService providerUserService;
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response){
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        User user = new User();
        user.setPassWord(passWord);
        user.setUserName(userName);
        System.out.println(userName);
        System.out.println(passWord);
        logger.info("请求服务");
        user = providerUserService.login(user);
        System.out.println(user);
        if(user != null){
            request.getSession().setAttribute("user",user);
            return "/success";
        }else
            return "/failure";
    }
}
