package com.example.mysqllearningdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mysqllearningdemo.mapper.HistoryMapper;
import com.example.mysqllearningdemo.pojo.History;
import com.example.mysqllearningdemo.service.HistoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HistoryServiceImpl extends ServiceImpl<HistoryMapper, History> implements HistoryService {


}
