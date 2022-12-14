package com.zjm.reids_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @PROJECT_NAME: reids_spring
 * @PACKAGE_NAME: com.zjm.reids_spring.controller
 * @author: 赵嘉盟-HONOR
 * @data: 2022-09-25 13:22
 * @DESCRIPTION
 */
@RestController
@RequestMapping("/redisTest")
public class Redis_Controller {
    @Autowired
    private RedisTemplate redisTemplate;
    @GetMapping
    public String testRedis(){
        //设置值到redis
        redisTemplate.opsForValue().set("name", "lucy");
        //从redis获取值
        String name =(String) redisTemplate.opsForValue().get("name");
        return name;
    }

    @GetMapping("testLock")
    public void testLock(){
        //1获取锁，setne
        //Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "111");
        Boolean lock = redisTemplate.opsForValue().setIfAbsent("lock", "111",3, TimeUnit.SECONDS);  //s设置过期时间（值，单位）
        //2获取锁成功、查询num的值
        if(lock){
            Object value = redisTemplate.opsForValue().get("num");
            //2.1判断num为空return
            if(StringUtils.isEmpty(value)){
                return;
            }
            //2.2有值就转成成int
            int num = Integer.parseInt(value+"");
            //2.3把redis的num加1
            redisTemplate.opsForValue().set("num", ++num);
            //2.4释放锁，del
            redisTemplate.delete("lock");
        }else{
            //3获取锁失败、每隔0.1秒再获取
            try {
                Thread.sleep(100);
                testLock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
