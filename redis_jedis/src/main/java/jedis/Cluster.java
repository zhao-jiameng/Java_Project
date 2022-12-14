package jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @PROJECT_NAME: redis_jedis
 * @PACKAGE_NAME: jedis
 * @author: 赵嘉盟-HONOR
 * @data: 2022-10-03 20:45
 * @DESCRIPTION
 */
public class Cluster {
    public static void main(String[] args) {
        //創建對象
        HostAndPort hostAndPort = new HostAndPort("192.168.174.100", 6379);//去中心化鏈接
        JedisCluster jedisCluster = new JedisCluster(hostAndPort);

        //進行操作
        jedisCluster.set("v1", "value1");

        String v1 = jedisCluster.get("v1");
        System.out.println(v1);

        jedisCluster.close();
    }
}
