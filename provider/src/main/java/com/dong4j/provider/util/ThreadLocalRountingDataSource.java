package com.dong4j.provider.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

import javax.sql.DataSource;
import java.util.Map;

/**
 * Created by Code.Ai on 16/7/23.
 * Description:
 */
public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {
    private Map<Object, DataSource> targetDataSources;
    private Object                  defaultTargetDataSource;
    private DataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
    private Map<Object, DataSource> resolvedDataSources;
    private DataSource              resolvedDefaultDataSource;

    @Override
    protected Object determineCurrentLookupKey() {
        // 返回哪个 key 从而获取相应的 datasource
        return DataSourceKeyManager.get();
    }
}
