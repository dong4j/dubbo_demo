package com.dong4j.provider;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Code.Ai on 16/7/19.
 * Description:
 */
public class MainTest {
    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] { "spring-mybatis.xml" });
        context.start();
        // 为保证服务一直开着，利用输入流的阻塞来模拟
        System.in.read();
    }
}
