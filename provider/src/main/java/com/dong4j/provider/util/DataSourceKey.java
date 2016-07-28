package com.dong4j.provider.util;


import com.dong4j.provider.common.KeyRecycleLink;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Code.Ai on 16/7/23.
 * Description:
 */
public class DataSourceKey {
    // 调用Zookeeper 获取节点信息
    @Autowired
    private KeyRecycleLink keyRecycleLink;
    private Object         defaultKey;
    public DataSourceKey(){
    }

    public  KeyRecycleLink getKeyRecycleLink() {
        return this.keyRecycleLink;
    }

    public void setKeyRecycleLink(KeyRecycleLink keyRecycleLink) {
        this.keyRecycleLink = keyRecycleLink;
    }

    public Object getDefaultKey(){
        return this.keyRecycleLink.getHeaderData();
    }
}
