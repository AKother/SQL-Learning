package com.example.mysqllearningdemo.controller;


import com.alibaba.fastjson.JSON;
import com.example.mysqllearningdemo.common.Result;
import com.example.mysqllearningdemo.pojo.History;
import com.example.mysqllearningdemo.pojo.User;
import com.example.mysqllearningdemo.pojo.dto.UserDTO;
import com.example.mysqllearningdemo.pojo.vo.ExecuteSQLDTO;
import com.example.mysqllearningdemo.service.impl.HistoryServiceImpl;
import com.example.mysqllearningdemo.service.impl.SQLServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@Api(value = "SQL",tags = {"SQL"})
@RequestMapping("/sql")
@RestController
public class SQLController {

    @Autowired
    SQLServiceImpl sqlService;

    @Autowired
    HistoryServiceImpl historyService;

    @Transactional
    @ApiOperation("执行SQL并拿到返回结果")
    @PostMapping("/execute")
    public Result executeSQL(@RequestBody ExecuteSQLDTO executeSQLDTO){
        Result result = sqlService.executeSQL(executeSQLDTO.getUserId(), executeSQLDTO.getSql());
        History history = new History();
        history.setSql(executeSQLDTO.getSql());
        history.setResult(JSON.toJSONString(result.getData()));
        history.setUserId(Integer.valueOf( executeSQLDTO.getUserId() ));
        history.setIsStar(0);
        historyService.save(history);
        return result;
    }

    @ApiOperation(value = "查看用户创建的表",notes = "返回的只是表名，此处表名即是表ID")
    @PostMapping("/show/tables")
    public Result showTables(@RequestBody UserDTO userDTO){
        return sqlService.getTablesByUser(userDTO.getUserId());
    }

    @ApiOperation("查看表全部内容")
    @PostMapping ("/table/details")
    public Result getTableDetails(@RequestBody UserDTO userDTO){
        return sqlService.getTableDetails(userDTO.getUserId(), userDTO.getTableName());
    }

}
