package com.example.mysqllearningdemo.utils;

import com.example.mysqllearningdemo.pojo.User;

//保存登录后的用户信息（多线程）
public class UserThreadLocal {

    private UserThreadLocal(){} //私有构造方法

    //线程变量隔离（每个线程都有各自的LOCAL，互不干扰）  ThreadLocal屏蔽了线程间的通讯，避免了多线程问题
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>(); //唯一初始化变量LOCAL

    public static void put(User user){
        LOCAL.set(user);   //将user放入
    }

    public static User get(){
        return LOCAL.get();
    }

    public static Integer getUserId() { return get().getId(); }

    public static void remove(){
        LOCAL.remove();
    }
}
