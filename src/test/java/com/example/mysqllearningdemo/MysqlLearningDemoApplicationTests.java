package com.example.mysqllearningdemo;

import com.example.mysqllearningdemo.utils.RedisUtils;
import com.google.common.collect.ImmutableMap;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootTest
class MysqlLearningDemoApplicationTests {

    @Autowired
    RedisUtils redisUtils;

    @Test
    void testRedis(){
        redisUtils.set("key", "value");
        System.out.println(redisUtils.get("key"));
    }

    @Test
    void testNullPointerException(){
        throw new NullPointerException();
    }

}
