package com.example.mysqllearningdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Override   //跨域配置
//    public void addCorsMappings(CorsRegistry registry) {
//        //跨域配置，不可设置为*，不安全, 前后端分离项目，可能域名不一致
//        //本地测试 端口不一致 也算跨域                      //允许该网址（8085端口）访问本域名
//        registry.addMapping("/**")
//                .allowedOrigins("*")
//                .allowedHeaders("*")
//                .allowedMethods("*")
//                .allowedOriginPatterns("*");
//
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/swagger-resource")
                .excludePathPatterns("/swagger-resources/**")
                .excludePathPatterns("/swagger-ui/*")
                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/v3/api-docs")
                .excludePathPatterns("/doc.html")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/email/code");
    }

}
