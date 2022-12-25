package com.example.mysqllearningdemo.service.impl;

import com.example.mysqllearningdemo.MysqlLearningDemoApplication;
import com.example.mysqllearningdemo.mapper.SQLDao;
import com.example.mysqllearningdemo.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MysqlLearningDemoApplication.class)
class SQLServiceImplTest {

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    SQLDao sqlDao;

    @Test
    void testRedis(){
        redisUtils.set("key", "value");
        System.out.println(redisUtils.get("key"));
    }

    @Test
    void testCreateDatabase() {
        try{
            sqlDao.createDatabase("1482089698@qq.com");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}