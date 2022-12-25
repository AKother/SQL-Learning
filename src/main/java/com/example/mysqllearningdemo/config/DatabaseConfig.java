package com.example.mysqllearningdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


public class DatabaseConfig {


    public static String SPRING_DATASOURCE_USERNAME = "root";

    public static String SPRING_DATASOURCE_PASSWORD = "Akother721202";

    public static String SPRING_DATASOURCE_URL = "jdbc:mysql://localhost:3306/ml?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf8&useSSL=false";

}
