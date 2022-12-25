package com.example.mysqllearningdemo;

import com.example.mysqllearningdemo.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MysqlLearningDemoApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Test
    void testRedis(){
        redisUtils.set("key", "value");
        System.out.println(redisUtils.get("key"));
    }

}
