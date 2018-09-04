package com.wp.myboot;


import com.wp.myboot.RedisSessionConfig.RedisUtil;
import com.wp.myboot.service.Mp4Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisAutoTest {


    @Autowired
    private RedisUtil redisUtil;


    @Resource
    private Mp4Service mp4Service;

    @Test
    public void testRedis(){
        //验证key是否存在；true存在，false不存在
        //redis.hasKey("key");
        //设置key=1
        //redis.opsForValue().set("key", "1");
        //读取key的值
        //redis.opsForValue().get("key");
        //将key的值+1 ；  如果要减1 就填入 -1
        //redis.boundValueOps("key").increment(1);
        //删除某key
        //redis.delete("key");

//        stringRedisTemplate.opsForValue().set("zzp","bigz");
//        Assert.assertEquals("bigz",stringRedisTemplate.opsForValue().get("zzp"));

        //10秒
        String syt = (String) redisUtil.get("18300247760");
        System.out.println(syt);


    }


    @Test
    public void testaveFeedback(){
//        mp4Service.saveFeedback("18300247760","sdfhbsadfhbsadhfbasdf","2018-09-01 00:00:00");
        for (int i=0;i<10;i++){
            System.out.println(new Random().nextInt(2));
        }

    }
}
