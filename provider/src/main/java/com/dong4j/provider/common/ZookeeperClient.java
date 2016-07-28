package com.dong4j.provider.common;

import com.dong4j.provider.util.DataSourceKey;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Code.Ai on 16/7/24.
 * Description:
 */
public class ZookeeperClient {
    @Autowired
    private DataSourceKey dataSourceKey;
    private String groupNode = "datasource";
    private ZooKeeper zk;
    private Stat stat = new Stat();
    private volatile List<String> serverList;
    private          WatchedEvent event1;

    public DataSourceKey getDataSourceKey() {
        return dataSourceKey;
    }

    public void setDataSourceKey(DataSourceKey dataSourceKey) {
        this.dataSourceKey = dataSourceKey;
    }

    /**
     * 连接zookeeper
     */
    public void connectZookeeper() throws Exception {
        //ApplicationContext ac = new FileSystemXmlApplicationContext("classpath:spring-mybatis.xml");
        //dataSourceKey = (DataSourceKey)ac.getBean("dataSourceKey");
        //keyRecycleLink = new KeyRecycleLink();
        //dataSourceKey = new DataSourceKey();
        //dataSourceKey.setKeyRecycleLink(keyRecycleLink);
        // Watcher实例
        Watcher wh = new Watcher() {
            public void process(WatchedEvent event) {
                event1 = event;
                // 如果发生了"/datasource"节点下的子节点变化事件, 更新server列表, 并重新注册监听
                if (event.getType() == Event.EventType.NodeChildrenChanged && ("/" + groupNode).equals(event.getPath())) {
                    try {
                        updateServerList();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        zk = new ZooKeeper("127.0.0.1:2181", Integer.MAX_VALUE, wh);
        System.out.println("初始化:" + zk.getChildren("/" + groupNode, false));
        updateServerList();
    }

    /**
     * 更新server列表
     */
    private void updateServerList() throws Exception {
        List<String> newServerList = new ArrayList<String>();
        // 获取并监听groupNode的子节点变化
        // watch参数为true, 表示监听子节点变化事件.
        // 每次都需要重新注册监听, 因为一次注册, 只能监听一次事件, 如果还想继续保持监听, 必须重新注册
        List<String> subList = zk.getChildren("/" + groupNode, true);

        for (String subNode : subList) {
            // 获取每个子节点下关联的server地址
            byte[] data = zk.getData("/" + groupNode + "/" + subNode, false, stat);
            newServerList.add(new String(data, "utf-8"));
        }
        Collections.sort(newServerList);
        System.out.println("排序后 " + newServerList);
        System.out.println("updateNode前:  newServerList=" + newServerList + ",serverList= " + serverList);
        updateNode(newServerList, serverList);
        // 替换server列表
        serverList = newServerList;
        System.out.println("updateNode后:  newServerList=" + newServerList + ",serverList= " + serverList);
    }

    /**
     * client的工作逻辑写在这个方法中
     * 此处不做任何处理, 只让client sleep
     */
    //public void handle() throws InterruptedException {
    //    Thread.sleep(Long.MAX_VALUE);
    //}
    public ZooKeeper getZk() {
        return zk;
    }

    public void dataSourceWatcher() throws KeeperException, InterruptedException {
        Stat stat = zk.exists("/datasource/datasource1", false);
        if (stat != null) {
            System.out.println(stat.getNumChildren());
        }
    }

    /**
     * 更新节点
     */
    public synchronized void updateNode(List<String> newServerList, List<String> serverList) {
        int newSize = 0;
        int oldSize = 0;
        // 初始化
        if (serverList == null) {
            if (newServerList.size() >= 1) {
                dataSourceKey.getKeyRecycleLink().getHeader().value = newServerList.get(0);
                System.out.println("初始化列表: header=" + dataSourceKey.getKeyRecycleLink().getHeader().value);
                for (int i = 1; i < newServerList.size(); i++) {
                    addNode(newServerList.get(i));
                }
            } else if (newServerList.size() == 0) {
                System.out.println("初始化 不处理");
            }
        } else if (newServerList.size() == 0 && serverList.size() != 0) {
            for (String delNode : serverList) {
                deleteNode(delNode);
            }
        } else if (newServerList.size() != 0 && serverList.size() == 0) {
            if (newServerList.size() == 1) {
                dataSourceKey.getKeyRecycleLink().getHeader().value = newServerList.get(0);
                System.out.println("初始化列表: header=" + dataSourceKey.getKeyRecycleLink().getHeader().value);
            } else {
                for (int i = 1; i < newServerList.size(); i++) {
                    addNode(newServerList.get(i));
                }
            }
        } else {
            while (newSize <= newServerList.size() - 1 || oldSize <= serverList.size() - 1) {
                if (newSize >= newServerList.size() && oldSize <= serverList.size() - 1) {
                    deleteNode(serverList.get(oldSize));
                } else if (newSize <= newServerList.size() - 1 && oldSize >= serverList.size()) {
                    addNode(newServerList.get(newSize));
                } else {
                    if (!newServerList.get(newSize).equals(serverList.get(oldSize))) {
                        addNode(newServerList.get(newSize));
                        deleteNode(serverList.get(oldSize));
                    }
                }
                newSize++;
                oldSize++;
            }
        }
    }

    /**
     * 增加节点
     */
    public void addNode(Object node) {
        System.out.println("增加节点:" + node);
        dataSourceKey.getKeyRecycleLink().insertList(node);
        System.out.println(dataSourceKey.getKeyRecycleLink().size());
        dataSourceKey.getKeyRecycleLink().print();
    }

    /**
     * 减去节点
     */
    public void deleteNode(Object node) {
        System.out.println("删除节点" + node);
        dataSourceKey.getKeyRecycleLink().deletelist(node);
        System.out.println(dataSourceKey.getKeyRecycleLink().size());
        dataSourceKey.getKeyRecycleLink().print();
    }
}
