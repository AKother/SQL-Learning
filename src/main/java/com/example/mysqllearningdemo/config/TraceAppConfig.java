package com.example.mysqllearningdemo.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Aspect
@Configuration
public class TraceAppConfig {

    @Pointcut("execution(* com.example.mysqllearningdemo.controller..*.*(..))")
    public void monitor() {}

    @Bean
    public Advisor traceAdvisor() {
        CustomizableTraceInterceptor customizableTraceInterceptor = new CustomizableTraceInterceptor();
        customizableTraceInterceptor.setEnterMessage("进入方法:$[methodName], 参数:$[arguments]");
        customizableTraceInterceptor.setExitMessage("退出方法:$[methodName]，返回值:$[returnValue]");
        customizableTraceInterceptor.setUseDynamicLogger(true);
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.example.mysqllearningdemo.config.TraceAppConfig.monitor()");
        return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor);
    }

}
