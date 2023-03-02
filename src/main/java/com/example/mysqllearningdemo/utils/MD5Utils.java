package com.example.mysqllearningdemo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

@Component
public class MD5Utils {

    public static String MD5EncryptPassword(String userName,String password){
        String hashAlgorithmName = "MD5";// 加密方式
        ByteSource salt = ByteSource.Util.bytes(userName);// 以账号作为盐值
        int hashIterations = 1024;// 加密1024次
        String newPassword = String.valueOf(new SimpleHash(hashAlgorithmName, password,salt,hashIterations));
        return newPassword;
    }

    public static void main(String[] args) {
    }
}
