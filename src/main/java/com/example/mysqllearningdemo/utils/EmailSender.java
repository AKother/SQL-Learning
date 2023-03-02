package com.example.mysqllearningdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EmailSender {

    @Autowired
    MailSender mailSender;

    public void sendEmail(String receiver,String title,String content) {
        SimpleMailMessage msg = new SimpleMailMessage();
        //发件人
        msg.setFrom("1482089698@qq.com");
        //收件人
        msg.setTo(receiver);
        //邮件主题
        msg.setSubject(title);
        //邮件内容
        msg.setText(content); //content可以作为验证码
        mailSender.send(msg);
    }

    /**
     * 获取验证码
     */
    public String getEmailCode(){
        String emailCode = String.valueOf((int)(Math.random()*8998)+1000+1);
        return emailCode;
    }

    /**
     * 发送注册验证码
     */
    public void sendRegisterEmail(String email, String emailCode){
        sendEmail(email,"SQL在线学习平台注册验证码","感谢您注册SQL在线学习平台，您的注册验证码为:"+emailCode);
    }

    /**
     * 密码重置验证码
     */
    public void sendForgetEmail(String email,String emailCode){
        sendEmail(email,"SQL在线学习平台重置密码验证码","您重置密码的验证码为："+emailCode);
    }
}
