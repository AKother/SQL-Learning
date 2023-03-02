package com.example.mysqllearningdemo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class JWTInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        Result result = null;
//        String token = request.getHeader("token");
//        try {
//            JwtUtils.checkToken(token);
//            log.info("验证成功，token值：{}", token);
//        } catch (SignatureVerificationException e) {
//            result = new Result(false, "无效签名");
//        } catch (TokenExpiredException e) {
//            result = new Result(false, "token过期");
//        } catch (AlgorithmMismatchException e) {
//            result = new Result(false, "token算法不一致");
//        } catch (Exception e) {
//            result = new Result(false, "Token无效");
//        }
//        String json = new ObjectMapper().writeValueAsString(result);
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().println(json);
//        log.info("token验证失败");
//        return false;
        return true;
    }

}

