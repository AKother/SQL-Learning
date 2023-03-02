package com.example.mysqllearningdemo.pojo.vo;

import lombok.Data;

@Data
public class LoginVO {

    private String email;

    private String password;

    private boolean rememberMe;

}
