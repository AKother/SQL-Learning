package com.example.mysqllearningdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mysqllearningdemo.mapper.UserMapper;
import com.example.mysqllearningdemo.pojo.User;
import com.example.mysqllearningdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}

