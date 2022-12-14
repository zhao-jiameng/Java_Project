package com.zjm.zookeeper;

import org.apache.log4j.chainsaw.Main;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * 模拟服务器上下线
 */
public class Server {
    private String connectString="hadoop101:2181,hadoop102:2181,hadoop103:2181";
    private int sessionTimeout=10000;
    private ZooKeeper zooKeeper;
    private String serverZnode="/servers";
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        Server server = new Server();
        //1.初始化连接
        server.initConerct();
        //2.在zookeeper中的某一个Znode中创建临时节点
        server.regist(args[0]);
        //3.保持连接状态
        TimeUnit.MILLISECONDS.sleep(Long.MAX_VALUE);
    }

    private void regist(String data) throws InterruptedException, KeeperException {
        Stat exists = zooKeeper.exists(serverZnode, false);
        if (exists==null){
             zooKeeper.create(serverZnode, "servers".getBytes(StandardCharsets.UTF_8)
                    , ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        }
        //在当前节点创建服务器对应的Znode
        String s = zooKeeper.create(serverZnode + "/server", data.getBytes(StandardCharsets.UTF_8)
                , ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println(s+"Is On Line(已经上线)");
    }

    private void initConerct() throws IOException {
        zooKeeper=new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
