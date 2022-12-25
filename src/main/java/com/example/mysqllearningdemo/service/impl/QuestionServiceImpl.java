package com.example.mysqllearningdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mysqllearningdemo.mapper.QuestionMapper;
import com.example.mysqllearningdemo.pojo.Question;
import com.example.mysqllearningdemo.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {


}
