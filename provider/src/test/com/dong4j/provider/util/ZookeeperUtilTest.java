package com.dong4j.provider.util;

import com.dong4j.provider.common.KeyRecycleLink;
import com.dong4j.provider.common.ZookeeperClient;
import org.apache.zookeeper.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Code.Ai on 16/7/23.
 * Description:
 */
public class ZookeeperUtilTest {

    @Test
    public void testMethod() throws Exception {
    }

    @Test
    public void test1() throws Exception {
        ZookeeperClient zookeeperClinet = new ZookeeperClient();

        KeyRecycleLink keyRecycleLink = new KeyRecycleLink("DATASOURCE1");
        DataSourceKey dataSourceKey = new DataSourceKey();
        dataSourceKey.setKeyRecycleLink(keyRecycleLink);
        zookeeperClinet.setDataSourceKey(dataSourceKey);

        zookeeperClinet.connectZookeeper();
        ZooKeeper zk = zookeeperClinet.getZk();
        //System.out.println(zk.getState().name());
        System.in.read();
    }

    @Test
    public void test3() {
        List list1 = new ArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        //list1.add("7");
        //list1.add("9");

        List list2 = new ArrayList();
        list2.add("1");
        list2.add("2");
        list2.add("3");
        list2.add("5");

        //并集
        //list1.addAll(list2);
        //差集
        //list1.removeAll(list2);
        //list2.removeAll(list1);

        List temp = list1;
        if(list1.size() >= list2.size()){
            list1.removeAll(list2);
            System.out.println("增加");
            list2.removeAll(temp);
        }


        //无重复并集
        //list2.removeAll(list1);
        //list1.addAll(list2);
        //交集
        //list1.retainAll(list2);

        //Iterator<String> it1 = list1.iterator();
        //while (it1.hasNext()) {
        //    System.out.println(it1.next());
        //}
        Iterator<String> it2 = list2.iterator();
        while (it2.hasNext()) {
            System.out.println(it2.next());
        }
    }
}