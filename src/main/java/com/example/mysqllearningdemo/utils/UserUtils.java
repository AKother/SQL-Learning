package com.example.mysqllearningdemo.utils;


import com.example.mysqllearningdemo.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {

    //直接获取当前用户
    public User getUser(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return (User) session.getAttribute("user");
    }

    //获取用户姓名
    public String getUserName(){
        return getUser().getName();
    }

    //获取用户id
    public int getUserId(){
        return getUser().getId();
    }

    //获取用户头像
    public String getUserAvatar(){
        return getUser().getAvatar();
    }

    //获取用户Email
    public String getUserEmail(){
        return getUser().getEmail();
    }

    //获取用户密码
    public String getUserPassword(){
        return getUser().getPassword();
    }


}
