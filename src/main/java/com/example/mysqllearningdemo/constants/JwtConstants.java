package com.example.mysqllearningdemo.constants;

public class JwtConstants {

    // 密钥
    public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

    // 过期时间
    public static final long EXPIRATION_TIME = 60 * 60L;
    public static final long EXPIRATION_TIME_REMEMBER = 60 * 60 * 24 * 7L;

    // JWT token defaults
    public static final String TOKEN = "token";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";

}
