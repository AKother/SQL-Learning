package com.example.mysqllearningdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mysqllearningdemo.mapper.UserMapper;
import com.example.mysqllearningdemo.pojo.User;
import com.example.mysqllearningdemo.service.UserService;
import com.example.mysqllearningdemo.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

}

