package com.example.mysqllearningdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mysqllearningdemo.mapper.TutorialMapper;
import com.example.mysqllearningdemo.pojo.Tutorial;
import com.example.mysqllearningdemo.service.TutorialService;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TutorialServiceImpl extends ServiceImpl<TutorialMapper, Tutorial> implements TutorialService {


}

