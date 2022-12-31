package com.example.mysqllearningdemo.controller;

import com.example.mysqllearningdemo.common.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/resourceNotFound")
    public Result throwException() {
        throw new NullPointerException();
    }
}
