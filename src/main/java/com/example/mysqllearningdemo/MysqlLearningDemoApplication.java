package com.example.mysqllearningdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@MapperScan("com.example.mysqllearningdemo.mapper")
@SpringBootApplication
public class MysqlLearningDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlLearningDemoApplication.class, args);
    }

}
