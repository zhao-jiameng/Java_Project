package com.zjm.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Client {
    private String connectString="hadoop101:2181,hadoop102:2181,hadoop103:2181";
    private int sessionTimeout=10000;
    private ZooKeeper zooKeeper;
    private String serverZnode="/servers";
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        Client client = new Client();
        //1.初始化Zookeeper客户端连接
        client.initCoonnect();
        //2.获取在线的服务器列表
        client.listAllServers();
        //3.保持连接
        TimeUnit.MILLISECONDS.sleep(Long.MAX_VALUE);
    }

    private void listAllServers() throws InterruptedException, KeeperException {     //循环监听
        List<String> children = zooKeeper.getChildren(serverZnode, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                //重新获取当前服务器
                try {
                    listAllServers();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (KeeperException e) {
                    e.printStackTrace();
                }
            }
        });
        System.out.println("当前在线服务器为"+children);
    }

    private void initCoonnect() throws IOException {
        zooKeeper=new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }
}
