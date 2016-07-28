package com.dong4j.provider.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Code.Ai on 16/7/25.
 * Description:
 */
@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceKeyConfig {
    @Value("${nowkey}")
    private static String datasourceKey;

    public static String getDatasourceKey() {
        System.out.println(datasourceKey);
        return datasourceKey;
    }

    public static void setDatasourceKey(String datasourceKey) {
        DataSourceKeyConfig.datasourceKey = datasourceKey;
    }
}
