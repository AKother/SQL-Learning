package com.example.mysqllearningdemo.controller;


import com.example.mysqllearningdemo.common.Result;
import com.example.mysqllearningdemo.pojo.vo.ExecuteSQLDTO;
import com.example.mysqllearningdemo.service.impl.SQLServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "SQL",tags = {"SQL"})
@RequestMapping("/sql")
@RestController
public class SQLController {

    @Autowired
    SQLServiceImpl sqlService;

    @ApiOperation("执行SQL并拿到返回结果")
    @PostMapping("/execute")
    public Result executeSQL(@RequestBody ExecuteSQLDTO executeSQLDTO){
        return sqlService.executeSQL(executeSQLDTO.getUserId(), executeSQLDTO.getSql());
    }

    @ApiOperation(value = "查看用户创建的表",notes = "返回的只是表名，此处表名即是表ID")
    @GetMapping("/show/tables")
    public Result showTables(@RequestParam("userId") String userId){
        return sqlService.getTablesByUser(userId);
    }

    @ApiOperation("查看表全部内容")
    @GetMapping("/table/details")
    public Result getTableDetails(@RequestParam("userId") String userId,
                                  @RequestParam("tableName") String tableName){
        return sqlService.getTableDetails(userId, tableName);
    }
}
