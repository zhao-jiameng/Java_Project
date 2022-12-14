package com.zjm.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 客户端性质开发
 * 1。客户端对象获取
 * 2.调用API方法
 * 3、关闭
 */
public class TestZookeeper {
    private String connectString="hadoop101:2181,hadoop102:2181,hadoop103:2181";
    //minSessionTimeout=400         <=指定的sessionTimeout         <=maxSessionTimeout=40000
    private int sessionTimeout=10000;
    private ZooKeeper zooKeeper;
    @Before
    public void init() throws IOException, InterruptedException, KeeperException {
        /**
         * 1.connectString  指定Zookeeper集群位置
         * 2.sessionTimeout 客户端超时时间
         * 3.Watcher        默认监听对象
         */
         zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //对事件处理的过程
            }
        });

    }
    /**
     * 创建节点
     * 位置
     * 内容
     * ACL权限（o开放，c创建者，e只读）
     * 临时|带序号（p持久，e临时，后缀带序号）
     * return 路径
     */
    @Test
    public void testCreate() throws InterruptedException, KeeperException, IOException {

        String s = zooKeeper.create("/zjm", "kuai".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("s="+s);
    }

    /**
     * 获取Znode的子节点
     *      可选性监听
     */
    @Test
    public void teatListZnodes() throws InterruptedException, KeeperException {
        // List<String> children = zooKeeper.getChildren("/zjm", false);//不监听
        List<String> children = zooKeeper.getChildren("/zjm", new Watcher() {   //监听
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("监听到子节点发生改变");
            }
        });
        System.out.println("children="+children);
        Thread.sleep(Long.MAX_VALUE);   //保持方法不结束
    }
    /**
     * 判断节点是否存在
     *      可选监听
     */
    @Test
    public void testExists() throws InterruptedException, KeeperException {
        Stat exists = zooKeeper.exists("/zjm", false);
        //exists.getAversion();         获取stat内容
    }

    /**
     * 获取节点内容
     *      可选监听
     */
    @Test
    public void testGetDate() throws InterruptedException, KeeperException {
        byte[] data = zooKeeper.getData("/zjm", false, getStat("/zjm"));
        System.out.println("data:"+new String(data));
    }

    /**
     *获取Stat方法，配合获取节点内容方法配套使用
     */
    public Stat getStat(String path) throws InterruptedException, KeeperException {
        return zooKeeper.exists(path,false);
    }

    /**
     * 设置节点内容
     * version:版本号(-1:忽略版本检查)
     */
    @Test
    public void testSetData() throws InterruptedException, KeeperException {
        Stat stat = zooKeeper.setData("/zjm", "zjm".getBytes(StandardCharsets.UTF_8), getStat("/zjm").getVersion());
    }

    /**
     * 删除
     *  递归要自己写
     */
    @Test
    public void testDelete() throws InterruptedException, KeeperException {
        zooKeeper.delete("/zjm",-1);
    }
    @After
    public void close() throws InterruptedException {
        zooKeeper.close();
    }


}
