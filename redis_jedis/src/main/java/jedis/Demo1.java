package jedis;


import org.junit.Test;
import redis.clients.jedis.Jedis;

import javax.naming.Name;
import java.util.List;
import java.util.Set;

/**
 * @PROJECT_NAME: redis_jedis
 * @PACKAGE_NAME: jedis
 * @author: 赵嘉盟-HONOR
 * @data: 2022-09-02 15:58
 * @DESCRIPTION
 */
public class Demo1 {
    public static void main(String[] args) {
        //創建Jedis對象
        Jedis jedis=new Jedis("192.168.174.101",6379);
        //測試
        //jedis.auth("123");
        String ping = jedis.ping();
        System.out.println(ping);
        jedis.close();
    }

    //操作key
    @Test
    public void domo1(){
        //創建Jedis對象
        Jedis jedis=new Jedis("192.168.174.101",6379);

        //添加
        jedis.set("name", "lucy");
        //获取
        String neme = jedis.get("name");
        System.out.println(neme);

        //设置多个key-value
        jedis.mset("k1","v1","k2","v2");
        List<String> mget = jedis.mget("k1", "k2");
        System.out.println(mget);

        Set<String> keys = jedis.keys("*");
        for (String key : keys) {
            System.out.println(key);
        }
    }

    //操作list
    @Test
    public void demo2(){
        //創建Jedis對象
        Jedis jedis=new Jedis("192.168.174.101",6379);
        jedis.lpush("key1", "lucy","mary","jack");
        List<String> value = jedis.lrange("key1", 0, -1);
        System.out.println(value);
    }

    //操作set
    @Test
    public void demo3(){
        //創建Jedis對象
        Jedis jedis=new Jedis("192.168.174.101",6379);
        jedis.sadd("name", "lucy","mary","jack");
        Set<String> name = jedis.smembers("name");
        System.out.println(name);
    }

    //操作hash
    @Test
    public void demo4(){
        //創建Jedis對象
        Jedis jedis=new Jedis("192.168.174.101",6379);
        jedis.hset("users", "age","20");
        String hget = jedis.hget("users", "age");
        System.out.println(hget);
    }

    //操作zset
    @Test
    public void demo5(){
        //創建Jedis對象
        Jedis jedis=new Jedis("192.168.174.101",6379);
        jedis.zadd("zset", 100d, "yyds");
        Set<String> zset = jedis.zrange("zset", 0, -1);
        System.out.println(zset);
    }
}
