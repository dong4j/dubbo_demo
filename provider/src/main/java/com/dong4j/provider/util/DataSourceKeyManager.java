package com.dong4j.provider.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by Code.Ai on 16/7/23.
 * Description:
 */
public class DataSourceKeyManager {
    private static final ThreadLocal<Object> dataSourceTypes = new ThreadLocal<Object>();

    public static Object get(){
        return dataSourceTypes.get();
    }

    public static void set(Object dataSourceType){
        dataSourceTypes.set(dataSourceType);
    }

    public static void reset(){
        ApplicationContext ac            = new FileSystemXmlApplicationContext("classpath:spring-mybatis.xml");
        DataSourceKey      dataSourceKey = (DataSourceKey)ac.getBean("dataSourceKey");
        dataSourceTypes.set(dataSourceKey.getDefaultKey());
    }
}
