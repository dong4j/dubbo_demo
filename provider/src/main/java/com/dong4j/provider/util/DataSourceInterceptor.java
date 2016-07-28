package com.dong4j.provider.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by Code.Ai on 16/7/23.
 * Description:
 */
@Aspect    // for aop
@Component // for auto scan
@Order(0)  // execute before @Transactional
public class DataSourceInterceptor {
    Logger logger = LoggerFactory.getLogger(DataSourceInterceptor.class);
    @Pointcut("execution(public * com.dong4j.provider.providerservice..*.*(..))")
    public void login(){}

    @Before(value = "login()")
    public void before(JoinPoint jp) {
        logger.debug("设置 datasource");
        System.out.println("切入");
        //ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:spring-mybatis.xml");
        //DataSourceKey dataSourceKey = (DataSourceKey)ac.getBean("dataSourceKey");
        //DataSourceKeyManager.set(dataSourceKey.getDefaultKey());
    }
}
