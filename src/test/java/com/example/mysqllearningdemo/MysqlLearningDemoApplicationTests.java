package com.example.mysqllearningdemo;


import com.alibaba.fastjson.JSON;
import com.example.mysqllearningdemo.pojo.History;
import com.example.mysqllearningdemo.service.impl.HistoryServiceImpl;
import com.example.mysqllearningdemo.utils.RedisUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MysqlLearningDemoApplicationTests {

    @Autowired
    HistoryServiceImpl historyService;

    @Test
    void testSQL() {
        History history = new History();
        history.setSql("select * from xxx");
        history.setResult("1");
        history.setUserId(10);
        history.setIsStar(0);
        historyService.save(history);
    }

}
