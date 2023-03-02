package com.example.mysqllearningdemo.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Map;

@Slf4j
public class JwtUtils {

    private static String SING = "mysql-learning-demo";

    /**
     * 生成 jwt token
     */
    public static String createToken(Map<String, String> map) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE, 15);
        return JWT.create()
                .withPayload(map)
                .withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SING));
    }

    /**
     * 校验 jwt 的合法性
     */
    public static void checkToken(String token) {
        JWT.require(Algorithm.HMAC256(SING)).build().verify(token);
    }

    /**
     * 让token失效
     */
    public static boolean expireToken(Map<String, String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MILLISECOND, 1);
         JWT.create()
            .withPayload(map)
            .withExpiresAt(instance.getTime())
            .sign(Algorithm.HMAC256(SING));
         return true;
    }

}
